package com.tohacks2020.textmemaybe.controller;


import com.tohacks2020.textmemaybe.model.TextMe;
import com.twilio.Twilio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller


public class TextMeController {

    public static String testOutput;

    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

    public static final String ACCOUNT_SID = "add key here";
    public static final String AUTH_TOKEN = "add token here";

    public static void translate(String fromLang, String toLang, String text) throws Exception {
        // TODO: Should have used a 3rd party library to make a JSON string from an object
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"fromLang\":\"")
                .append(fromLang)
                .append("\",")
                .append("\"toLang\":\"")
                .append(toLang)
                .append("\",")
                .append("\"text\":\"")
                .append(text)
                .append("\"")
                .append("}")
                .toString();

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        System.out.println("Status Code: " + statusCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            testOutput = output;
        }
        conn.disconnect();
    }



    public static void main(String[] args) throws Exception {


        String fromLang = "en";
        String toLang = "fr";
        String text = "Let's have some fun!";

        TextMeController.translate(fromLang, toLang, text);



        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "";
        String to = "";
        String textMessage = testOutput;

        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(from), textMessage ).create();

        System.out.println(message.getSid());

    }




    @RequestMapping("/")
    public String greetingForm(Model model) {
        TextMe textme = new TextMe();


        model.addAttribute("textme", textme);

        return "home";
    }

    @RequestMapping(value="/home", method=RequestMethod.POST)
    public void greetingFormPost(@ModelAttribute("textme") TextMe textme) throws Exception {
        System.out.println(textme.getPhoneNumber());
        System.out.println(textme.getMessage());

        String fromLang = "en";
        String toLang = "fr";
        String text = textme.getMessage();

        TextMeController.translate(fromLang, toLang, text);



        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "+16413292748";
        String to = textme.getPhoneNumber();
        String textMessage = testOutput;

        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(from), textMessage ).create();

        System.out.println(message.getSid());

    }


}
