# Control_Home_Appliances_with_Flask_server_and_Android
In this project you can able to control your Home Appliance using Flask Server and Custom Android Application over Internet.  

Here we use RaspberryPi4 and relay board for the LED control. Here the Raspberry Pi4, is for running the backend program and send command to the relay board using GPIO pin.

Pin used: GPIO-16 (This is for single relay control)

Platoform:- Linux

Programming language:   
                        1. Python (for backend program)
                        2. JAVA   (for Android)
                      
Visit here for the video demo of our project:- https://www.youtube.com/watch?v=BInosZfLAh4

Operation:

1. For local communication between server and Android App , you can directly use your local IP wit hport number in your Android Codes.

2. For public communication between server and Android App, as per the demo video, we are using a cloud service called "localxpose". We also brought a domain from ("https://www.bigrock.in/") for our IP address.
  
  Process:-
  
      1. Download this service in your OS please follow this link:- https://www.localxpose.io/

      2. Install this service in Raspberry Pi, please follow this link:- https://docs.localxpose.io/tutorials/access-your-raspberry-pi-remotely  

      3. Reserve your domain with localxpose, please follow:- https://docs.localxpose.io/reservations/domain