#Control Home Appliances from Raspberry Pi using Android Application, (Backend-service)

import os
import RPi.GPIO as GPIO
from flask import Flask, request,render_template
value=" "
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

app = Flask(__name__)

#define actuators GPIOs
switch = 16

#initialize GPIO status variables
switchSts =0

#define switch pin as output
GPIO.setup(switch, GPIO.OUT)

#turn leds off
GPIO.output(switch, GPIO.LOW)

@app.route('/android',methods=["POST"])
def home():
    global value
    value=request.form['value']
    print(value)
    if value=="on" or value=="ON":
        print("Light is turned on")
        GPIO.output(switch,GPIO.HIGH)
        return "Light is turned on"

    if value=="off" or value=="Off":
        print("Light is turned Off")
        GPIO.output(switch,GPIO.LOW)
        return "Light is turned Off"
    return ("Succesfully sent")


@app.route('/send')
def Send():
    return "MSG Sent"
    

@app.route('/')
def view():
    global value
    print("Global variable: ",value)
    viewdata={'sent_value':value}
    return render_template("android.html",**viewdata)


if __name__ == "__main__":
    os.system("clear")
    app.run(host="0.0.0.0", port="80", debug= False)