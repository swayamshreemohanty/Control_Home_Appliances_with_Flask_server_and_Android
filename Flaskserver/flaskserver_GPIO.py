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
#switchSts =0

#define switch pin as output
GPIO.setup(switch, GPIO.OUT)

#turn leds off
GPIO.output(switch, GPIO.LOW)

@app.route('/send')
def Send():
    return "MSG Sent"

@app.route('/pull',methods=["POST"])
def home():
    global value
    value=request.form['value']
    print("Message Recieved",value)
    if value=="on" or value=="ON":
        print("Light is turned on")
        GPIO.output(switch,GPIO.HIGH)
        return "Light is turned on"

    if value=="off" or value=="Off":
        print("Light is turned Off")
        GPIO.output(switch,GPIO.LOW)
        return "Light is turned Off"
    return push()

@app.route('/push')
def push():
    print("Message sent to the client: ",value)
    return value

@app.route('/')
def view():
    global value
    print("Global variable: ",value)
    viewdata={'sent_value':value}
    return render_template("android.html",**viewdata)


if __name__ == "__main__":
    os.system("clear")
    app.run(host="0.0.0.0", port="80", debug= True)