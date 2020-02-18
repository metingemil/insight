#include <Servo.h>

Servo myServo;

const int potPin = A0;

int potVal;
int angle;

void setup() {
  myServo.attach(9);
  Serial.begin(9600); // open a serial port
}

void loop() {
  potVal = analogRead(potPin);
  Serial.print("potVal: ");
  Serial.print(potVal);

  angle = map(potVal, 0, 1023, 0, 179);

  Serial.print(", angle: ");
  Serial.println(angle);

  myServo.write(angle);
  delay(15);
}
