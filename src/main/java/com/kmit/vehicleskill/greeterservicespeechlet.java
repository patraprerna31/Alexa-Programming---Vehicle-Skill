package com.kmit.VehicleSkill;


import org.slf4j.Logger;
import java.util.Random;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.amazon.speech.ui.StandardCard;
import com.amazon.speech.slu.Slot;

/**
* This sample shows how to create a Lambda function for handling Alexa Skill requests that:
 * @author tele
 *
 */
public class GreeterServiceSpeechlet implements SpeechletV2 {
private static final Logger log = LoggerFactory.getLogger(GreeterServiceSpeechlet.class);

public static final  String Number = "vehicleno";
public static final  String Answer = "answer";
public static final  String Option = "option";
public static final String arr[][]= new String[][] {
 {"ts08fx5692","Prerna Patra", "S K Patra","SR: 456/78 HN: 34-77/20/39 Malkajgiri, Secunderabad"},
{"ts08fx2751","Sanjana Siripuram", "Eshwar Siripuram","HN:4-69/64/1,Vidyaranyapuri,Karimnagar"},
{"ts08fx3451","Vujjini Nikitha","Vujjini Surendherr Rao","HN:12-245,Hyd"}};

public static final String Color[]= new String[] {"Blue", "Red" ,"White", "Black" };
public static final String Fuel[]= new String[] {"Petrol" ,"Diesel", "CNG"};
public static final String Makers[]=new String[] {"Honda","Suziki","Bajaj"};
public static final String vehicleclass[]=new String[] {"Pulsar","Ktm","Activa"};
public static final String ChassisNumber[]=new String[] {"Z123456789","X124886232","B78994551365"};
public static final String EngineNumber[]=new String[] {"RTY878912","QWE45893","ASD5983"};
public static final String UnladenWeight[]=new String[] {"108"," 150","180"};
public static final String DateofRegistration[]=new String[] {"17/05/2000","14/06/2001","15/02/2002"};
public static final String MonthyearofManufacture []=new String[] {"1998","1990","1980"};
public static final String Challanupdates []=new String[]
		{"Amount to be paid: Rs500. Reason: Jumped a signal at naryanguda ",
		"Amount to be paid: Rs135. Reason: Travelled without Helmet",
		"Amount to be paid: Rs350. Reason: Triple riding"};
@Override
public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
    log.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
            requestEnvelope.getSession().getSessionId());

    // any initialization logic goes here
}

@Override
public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
    log.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
            requestEnvelope.getSession().getSessionId());

    String speechOutput =
           "Welcome to Alexa Vehicle skill. Please enter your vehicle number";
    // If the user either does not reply to the welcome message or says
    // something that is not understood, they will be prompted again with this text.
    String repromptText = "For instructions on what you can say, please say help me.";

    // Here we are prompting the user for input
    return newAskResponse(speechOutput, repromptText);
    
}

@Override
public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
    IntentRequest request = requestEnvelope.getRequest();
    Session session = requestEnvelope.getSession();
    log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
            requestEnvelope.getSession().getSessionId());

    Intent intent = request.getIntent();
    String intentName = (intent != null) ? intent.getName() : null;

    if ("FirstIntent".equals(intentName)) {
        return getHelloWorldIntent(intent,session);
    }
    else if ("YesornoIntent".equals(intentName)) {
        return getHelloWorldIntent1(intent,session);
    }
    else if ("OptionIntent".equals(intentName)) {
        return getHelloWorldIntent2(intent,session);
    }
    else if ("AMAZON.HelpIntent".equals(intentName)) {
        return getHelp();
    } else if ("AMAZON.StopIntent".equals(intentName)) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Goodbye");

        return SpeechletResponse.newTellResponse(outputSpeech);
    } else if ("AMAZON.CancelIntent".equals(intentName)) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Goodbye");

        return SpeechletResponse.newTellResponse(outputSpeech);
    } else {
        String errorSpeech = "This is unsupported.  Please try something else.";
        return newAskResponse(errorSpeech, errorSpeech);
    }
}

@Override
public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
    log.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
            requestEnvelope.getSession().getSessionId());

    // any cleanup logic goes here
}

/**
 * Creates a {@code SpeechletResponse} for the RecipeIntent.
 *
 * @param intent
 *            intent for the request
 * @return SpeechletResponse spoken and visual response for the given intent
 */
private SpeechletResponse getHelloWorldIntent(Intent intent,Session session) {
        
	Slot nameSlot = intent.getSlot("vehicleno");
	String value1 = nameSlot.getValue();
	session.setAttribute(Number, value1);
	String value = (String) session.getAttribute(Number);
        String resString= null;
        for(int i=0; i<3;i++)
        {
        	if(value.equals(arr[i][0]))
        	{
        		resString = "<speak>The basic details of the vehicle are<break time=\"1s\"/> \n"
        				+ "Owner’s Name : " + arr[i][1] +
        				"\n<break time=\"0.5s\"/>Owner’s Father Name : " + arr[i][2] +
        		"\n<break time=\"0.5s\"/> Owner’s Address : " + arr[i][3] +
        		"\n<break time=\"1s\"/> DO YOU WANT FURTHER INFORMATION?</speak>";
        		break;
        	}
        	else
        	{
        		resString = "Invalid Vehicle Number";
        	}
        }
        String responseText = resString;
 
        
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText(responseText);
            SsmlOutputSpeech SoutputSpeech=new SsmlOutputSpeech();
            SoutputSpeech.setSsml(responseText);
            SimpleCard card = new SimpleCard();
            card.setTitle("Intent Values  ");
            card.setContent(responseText);
            
            PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
            repromptOutputSpeech.setText("Hi");
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptOutputSpeech);


            return SpeechletResponse.newAskResponse(SoutputSpeech,reprompt, card);
        
   
     
}
private SpeechletResponse getHelloWorldIntent1(Intent intent,Session session) {
    
	Slot nameSlot = intent.getSlot("answer");
	String value2 = nameSlot.getValue();
	session.setAttribute(Answer, value2);
	String value3 = (String) session.getAttribute(Answer);
    String resString=null;
    if(value3.equals("yes"))
    {
    	resString= "<speak>Please enter the required choice\n" + 
    			"<break time=\"0.5s\"/>1.	Maker’s class\n" + 
    			"<break time=\"0.5s\"/>2.	Vehicle class\n" + 
    			"<break time=\"0.5s\"/>3.	Fuel Used\n" + 
    			"<break time=\"0.5s\"/>4.	Chassis Number\n" + 
    			"<break time=\"0.5s\"/>5.	Engine Number\n" + 
    			"<break time=\"0.5s\"/>6.	Unladen Weight \n" + 
    			"<break time=\"0.5s\"/>7.	Color\n" + 
    			"<break time=\"0.5s\"/>8.	Date of Registration\n" +  
    			"<break time=\"0.5s\"/>9.	Challan updates </speak>";
    }
    else if(value3.equals("no"))
    {
    	resString = "Thankyou for visiting our skill";
    }
    String responseText = resString;

    
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(responseText);
        SsmlOutputSpeech SoutputSpeech=new SsmlOutputSpeech();
        SoutputSpeech.setSsml(responseText);
        SimpleCard card = new SimpleCard();
        card.setTitle("Intent Values  ");
        card.setContent(responseText);
       /* String pictureURL = "https://s3.amazonaws.com/sonetpictures/Sunset-Wallpaper-Hd-001.jpg";
        StandardCard card = new StandardCard();
        card.setTitle("Challan picture ");
        card.setText(responseText);
        com.amazon.speech.ui.Image image = new com.amazon.speech.ui.Image(); 
        image.setLargeImageUrl(pictureURL); 
        card.setImage(image);*/
        
        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText("Hi");
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);


        return SpeechletResponse.newAskResponse(SoutputSpeech,reprompt, card);
    

 
}

private SpeechletResponse getHelloWorldIntent2(Intent intent,Session session) {
    
	/*Slot nameSlot = intent.getSlot("option");
	String value2 = nameSlot.getValue();
	session.setAttribute(Option, value2);
	String value = (String) session.getAttribute(Option);*/
	
	Slot nameSlot = intent.getSlot("choice");
	String value2 = nameSlot.getValue();
	session.setAttribute(Option, value2);
	
	//int value3 = (int) session.getAttribute(option);
    String resString=null;
    switch(value2)
    {
    case "1" :
    	double q = (Math.random()* (3-1)) + 1;
   	    int q1= (int)q;
   	    
   	    resString = "<speak>Maker’s class: " + Makers[q1] +"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "2" :
    	double w = (Math.random()* (3-1)) + 1;
   	    int w1 = (int)w;
   	    
   	    resString = "<speak>Vehicle class : " + vehicleclass[w1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "4" :
    	double e = (Math.random()* (3-1)) + 1;
   	    int e1 = (int)e;
   	    
   	    resString = "<speak>Chassis Number : " + ChassisNumber[e1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "5" :
    	double r = (Math.random()* (3-1)) + 1;
   	    int r1 = (int)r;
   	   
   	    resString = "<speak>Engine Number : " + EngineNumber[r1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "6" :
    	double t = (Math.random()* (3-1)) + 1;
   	    int t1 = (int)t;
   	    
   	    resString = "<speak>Unladen Weight : " + UnladenWeight [t1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "8" :
    	double u = (Math.random()* (3-1)) + 1;
   	    int u1 = (int)u;
   	    
   	    resString = "<speak>Date of Registration: " +DateofRegistration[u1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "9" :
    	double p = (Math.random()* (3-1)) + 1;
   	    int p1 = (int)p;
   	    
   	    resString = "<speak>Challan updates : \n" + Challanupdates[p1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
   	    break;
    case "3":
    	 double x = (Math.random()* (3-1)) + 1;
    	 int y = (int)x;
    	
    	 resString = "<speak>Fuel used : " + Fuel[y]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
    	 break;
    case "7":
    	double x1 = (Math.random()* (4-1)) + 1;
    	int y1 = (int)x1;
    	resString = "<speak>Color of the vehicle : " + Color[y1]+"\n<break time=\"0.5s\"/>DO YOU WANT FURTHER INFORMATION?</speak>";
    	break;
    default: 
    	resString = "Sorry. You chose a wrong option";
    	break;
    }
    String responseText = resString;

    
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(responseText);

        SimpleCard card = new SimpleCard();
        card.setTitle("Intent Values  ");
        card.setContent(responseText);
        
        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText("Hi");
        SsmlOutputSpeech SoutputSpeech=new SsmlOutputSpeech();
        SoutputSpeech.setSsml(responseText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);


        return SpeechletResponse.newAskResponse(SoutputSpeech,reprompt, card);
    

 
}

/**
 * Creates a {@code SpeechletResponse} for the HelpIntent.
 *
 * @return SpeechletResponse spoken and visual response for the given intent
 */
private SpeechletResponse getHelp() {
    String speechOutput =
            "You can say wish my friend";
    String repromptText =
    		"You can say wish my friend";
    return newAskResponse(speechOutput, repromptText);
}

/**
 * Wrapper for creating the Ask response. The OutputSpeech and {@link Reprompt} objects are
 * created from the input strings.
 *
 * @param stringOutput
 *            the output to be spoken
 * @param repromptText
 *            the reprompt for if the user doesn't reply or is misunderstood.
 * @return SpeechletResponse the speechlet response
 */
private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
	
	 SimpleCard card = new SimpleCard();
     card.setTitle(stringOutput);
    PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
    outputSpeech.setText(stringOutput);

    PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
    repromptOutputSpeech.setText(repromptText);
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(repromptOutputSpeech);

    return SpeechletResponse.newAskResponse(outputSpeech, reprompt,card);
}



}
