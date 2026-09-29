import java.util.*;
import java.io.*;

class NLP {
    String clean(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z ]", " ");
        return s;
    }
    boolean match(String input, String key) {
        input = clean(input);
        key = clean(key);
        return input.indexOf(key) != -1;
    }
}

class Brain {
    NLP nlp = new NLP();
    Hashtable kb = new Hashtable();
    String userName = "";

    Brain() {
        kb.put("hello", "Hello I am CodeAlpha Advance Bot with NLP");
        kb.put("hi", "Hi I am ready to help you");
        kb.put("codealpha", "CodeAlpha is EdTech Company with QR Certificates");
        kb.put("internship", "Perks are Offer Letter Certificate LOR Job Help");
        kb.put("java", "Java is OOP Language with File IO Collections");
        kb.put("projects", "Task1 Finance Engine Task2 Stock Task3 Chatbot");
        kb.put("skills", "Java OOP NLP FileIO Memory Learning");
        kb.put("help", "Try hello codealpha internship java projects skills time bye");
        kb.put("bye", "Goodbye Chat saved to history txt");
    }

    String getReply(String input) {
        String low = input.toLowerCase();
        if(low.indexOf("my name is") != -1) {
            userName = input.substring(low.indexOf("my name is") + 11).trim();
            return "Nice to meet you " + userName + " I remembered you";
        }
        if(low.indexOf("my name") != -1 && userName.length() > 0) {
            return "Your name is " + userName;
        }
        if(low.indexOf("time") != -1) {
            return "Current Time " + new Date().toString();
        }
        if(low.indexOf("learn") != -1 && low.indexOf("|") != -1) {
            try {
                int p = input.indexOf("|");
                String q = input.substring(5, p).trim().toLowerCase();
                String a = input.substring(p+1).trim();
                kb.put(q, a);
                return "Learned " + q + " = " + a;
            } catch(Exception e) {
                return "Use learn: question | answer";
            }
        }
        Enumeration e = kb.keys();
        while(e.hasMoreElements()) {
            String k = (String)e.nextElement();
            if(nlp.match(low, k)) {
                return (String)kb.get(k);
            }
        }
        return "I am learning You said " + input + " Try help";
    }
}

public class ChatbotAi {
    public static void main(String[] args) {
        System.out.println("CodeAlpha Advance Chatbot - All in One File");
        System.out.println("File: ChatbotAi.java");
        System.out.println("Type bye to exit\n");

        Brain brain = new Brain();
        Scanner sc = new Scanner(System.in);
        System.out.println("Bot: Hello I am CodeAlpha Advance Bot");

        while(true) {
            System.out.print("You: ");
            String input = sc.nextLine();
            String reply = brain.getReply(input);
            System.out.println("Bot: " + reply);

            try {
                FileWriter fw = new FileWriter("chat.txt", true);
                fw.write("You: " + input + "\nBot: " + reply + "\n");
                fw.close();
            } catch(Exception ex) {}

            if(input.toLowerCase().indexOf("bye") != -1) {
                break;
            }
        }
        sc.close();
        System.out.println("Task Completed - Chat saved in chat.txt");
    }
}
