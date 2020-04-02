package org.gopathon;

import org.gopathon.GaushalaManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 3/17/2020.
 */
public class HtmlParsing {

    static StringBuilder address = new StringBuilder();
    static StringBuilder contact = new StringBuilder();
    static StringBuilder website = new StringBuilder();
    static StringBuilder city = new StringBuilder();
    static StringBuilder state = new StringBuilder();
    static StringBuilder email = new StringBuilder();
    static boolean addressFlag = false;
    static boolean mobileFlag = false;
    static boolean websiteFlag = false;
    static boolean emailFlag = false;
    static List<Gaushala> gaushalaList = new ArrayList<Gaushala>();
    static WorksheetManager mgr = new WorksheetManager(gaushalaList);


    public static void main(String[] args) {
        try {
            GaushalaManager manager = new GaushalaManager();
            List<String> gaushalaNameList = new ArrayList<String>();
            gaushalaNameList = manager.getGaushalaNameList();
            //          String url = "https://www.indiancattle.com/directory/aravind-gau-seva-india-pvt-ltd-2/";
            //          Document doc = Jsoup.connect(url).get();
            synchronized (gaushalaList) {

                Thread worksheetThread = new Thread(() -> {
                    System.out.println("LIst is created size is : " + gaushalaList.size());
                    mgr.createFirstrow();
                });
                worksheetThread.start();

                for (String gaushalaName : gaushalaNameList
                        ) {
                    File input = new File("E:\\gaurav\\Learning\\Gopathon\\directory\\" + gaushalaName + "\\index.htm");
//                File input = new File("E:\\gaurav\\Learning\\Gopathon\\directory\\aaa-fun-n-food-pvt-ltd\\index.htm");
                Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
//
                Element content = doc.getElementById("directory_single");

                for (Node element : content.childNodes()) {
                    if (element.toString().length() > 0) {
                        if (    //element.toString().contains("Personal Email:") ||
                                element.toString().contains("Working Time:") ||
                                        element.toString().contains("Holidays:") ||
                                        element.toString().contains("Type:") ||
                                        element.toString().contains("Available Breeds:") ||
                                        element.toString().contains("Outdoor Services:") ||
                                        element.toString().contains("Available Products:") ||
                                        element.toString().contains("Conditions for accepting Stray Cattle:")
                                ) {
                            addressFlag = false;
                            mobileFlag = false;
                            websiteFlag = false;
                            emailFlag = false;
                            continue;
                        }

                        if (element.toString().contains("Address:")) {
                            addressFlag = true;
                            mobileFlag = false;
                            websiteFlag = false;
                            emailFlag = false;
                        } else if (element.toString().contains("Mobile")) {
                            mobileFlag = true;
                            addressFlag = false;
                            websiteFlag = false;
                            emailFlag = false;
                        } else if (element.toString().contains("Website:")) {
                            mobileFlag = false;
                            addressFlag = false;
                            emailFlag = false;
                            websiteFlag = true;
                        } else if (element.toString().contains("Email:")) {
                            mobileFlag = false;
                            addressFlag = false;
                            websiteFlag = false;
                            emailFlag = true;
                        }
                        if (element.childNodeSize() > 0 && (addressFlag || mobileFlag || websiteFlag || emailFlag)) {
                            extractChildren(element);
                        }
                    }
                }

                Gaushala gaushala = new Gaushala();
                gaushala.setGaushalaAddress(address.toString());
                   gaushala.setGaushalaName(gaushalaName);
                //gaushala.setGaushalaName("Test");
                gaushala.setContactNumber(contact.toString());
                gaushala.setWebsite(website.toString());
                gaushala.setState(state.toString());
                gaushala.setCity(city.toString());
                gaushala.setEmail(email.toString());
                gaushalaList.add(gaushala);
                System.out.println("gaushala size is " + gaushalaList.size());

                resetValues();
                if (gaushalaList.size() == 1) {
                    System.out.println("size greater than 1 so notifying");
                    gaushalaList.notify();
                }
//                if(gaushalaList.size()%50==0){
//                    System.out.println(Thread.currentThread().getName() + " is waiting now");
//                    //mgr.createFirstrow();
//                    gaushalaList.wait();
//                    System.out.println("Main thread is notified now ");
//
//                }
                  }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void resetValues() {
        address.setLength(0);
        state.setLength(0);
        city.setLength(0);
        website.setLength(0);
        contact.setLength(0);
        email.setLength(0);
    }

    private static StringBuilder decryptEmail(String encodedString) {
        StringBuilder email = new StringBuilder();
        int r = Integer.parseInt(encodedString.substring(0, 2), 16), i;
        for (int n = 2; n < encodedString.length(); n += 2) {
            i = Integer.parseInt(encodedString.substring(n, n + 2), 16) ^ r;
            char c = (char) i;
            email.append(Character.toString(c));
        }
        return email;
    }


    private static void extractChildren(Node element) {

        if (element.toString().contains("State:") && state.length() == 0) {
            state.append(element.childNode(2).toString());
        }

        if (element.toString().contains("District:") && city.length() == 0) {

            city.append(element.childNode(2).toString());
        }

        if (emailFlag && email.length() == 0) {

            // email.append(element.childNode(2).toString());
            email.append("Email : " + decryptEmail(element.toString().substring(element.toString().indexOf("#") + 1, element.toString().indexOf("target") - 2)));
        }

       /* if (child.toString().contains("Email") && !email.toString().contains("Email : ")) {
            website.append("Email : ");
            ((Element) child.parentNode).getElementsByAttribute("href").toString().substring(((Element) child.parentNode).getElementsByAttribute("href").toString().indexOf("#")+1,(((Element) child.parentNode).getElementsByAttribute("href").toString().indexOf("target")-2) )
        }*/

        for (Node child : element.childNodes()) {
            if (child.toString().contains("Village/Taluka/Block:") &&
                    !address.toString().contains("Village : ")) {
                address.append("Village : ");
            }
            if (child.toString().contains("Mobile") && !contact.toString().contains("Mobile : ")) {
                contact.append("Mobile : ");
            }

            if (child.toString().contains("Website") && !website.toString().contains("Website : ")) {
                website.append("Website : ");
            }


            if (child.childNodeSize() > 0) {
                extractChildren(child);
            } else {
                if (addressFlag
                        && !child.toString().contains("Village/Taluka/Block:")
                        && !child.toString().contains("Address:")
                        && !child.toString().contains("State:")
                        ) {
                    address.append(child.toString());
                } else if (mobileFlag && !child.toString().contains("Mobile")) {
                    contact.append(child.toString());
                } else if (websiteFlag && !child.toString().contains("Website")) {
                    website.append(child.toString());
                }
            }
        }
    }
}

