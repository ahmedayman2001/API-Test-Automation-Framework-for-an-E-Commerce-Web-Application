package MainPack;

import RequestBodyMessages.*;
import TestData.RandomDataGenerator;
import Utils.HelperClass;
import Utils.PropertiesReader;

public class Main {



    public static void main(String[] args) {


        //System.out.println("Hello");


//  String url = PropertiesReader.get("src/test/java/Properties/environment.properties", "baseUrl");
//
//        System.out.println(url);
//
//    }


        InvalidLogin tester = HelperClass.readJson
                ("src/main/java/TestData/Email_Contain_User_Details_Data.JSON", InvalidLogin.class);


        System.out.println(tester.getEmail());

//        for (ValidLogin it : tester ){
//
//            System.out.println("Email: " + it.getEmail());
//            System.out.println("Password: " + it.getPassword());
//
//        }



//
//        RegisterUser User = HelperClass.readJson
//                ("src/main/java/TestData/RegisterUserData.JSON", RegisterUser.class);
//
//        User.setName(RandomDataGenerator.generateRandomName());
//        User.setEmail(RandomDataGenerator.generateRandomEmail());
//        User.setName(RandomDataGenerator.generateRandomName());
//        User.setPassword(RandomDataGenerator.generateRandomPassword());
//
//
//            System.out.println("===================");
//            System.out.println(User.getEmail());
//            System.out.println(User.getName());
//            System.out.println(User.getPassword());
//
//        System.out.println("====================");



    }



    }
