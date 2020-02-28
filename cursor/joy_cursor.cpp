/* --------------------------------------------------
*  Name: YongQuan Zhang
*  ID: 1515873
*  CMPUT 275, Winter 2020
*  Weekly Exercise #1: Display and Joystick
*---------------------------------------------------*/

#define SD_CS 10
#define JOY_VERT  A9 // should connect A9 to pin VRx
#define JOY_HORIZ A8 // should connect A8 to pin VRy
#define JOY_SEL   53

#include <Arduino.h>

// core graphics library (written by Adafruit)
#include <Adafruit_GFX.h>

// Hardware-specific graphics library for MCU Friend 3.5" TFT LCD shield
#include <MCUFRIEND_kbv.h>

// LCD and SD card will communicate using the Serial Peripheral Interface (SPI)
// e.g., SPI is used to display images stored on the SD card
#include <SPI.h>

// needed for reading/writing to SD card
#include <SD.h>

#include "lcd_image.h"


MCUFRIEND_kbv tft;

#define DISPLAY_WIDTH  480
#define DISPLAY_HEIGHT 320
#define YEG_SIZE 2048

lcd_image_t yegImage = { "yeg-big.lcd", YEG_SIZE, YEG_SIZE };

#define JOY_CENTER   512
#define JOY_DEADZONE 64

#define CURSOR_SIZE 9

// the cursor position on the display
int cursorX, cursorY;

// forward declaration for redrawing the cursor
void redrawCursor(uint16_t colour);

void setup() {
  init();

  Serial.begin(9600);

	pinMode(JOY_SEL, INPUT_PULLUP);

	//    tft.reset();             // hardware reset
  uint16_t ID = tft.readID();    // read ID from display
  Serial.print("ID = 0x");
  Serial.println(ID, HEX);
  if (ID == 0xD3D3) ID = 0x9481; // write-only shield
  
  // must come before SD.begin() ...
  tft.begin(ID);                 // LCD gets ready to work

	Serial.print("Initializing SD card...");
	if (!SD.begin(SD_CS)) {
		Serial.println("failed! Is it inserted properly?");
		while (true) {}
	}
	Serial.println("OK!");

	tft.setRotation(1);

  tft.fillScreen(TFT_BLACK);

  // draws the centre of the Edmonton map, leaving the rightmost 60 columns black
	int yegMiddleX = YEG_SIZE/2 - (DISPLAY_WIDTH - 60)/2;
	int yegMiddleY = YEG_SIZE/2 - DISPLAY_HEIGHT/2;
	lcd_image_draw(&yegImage, &tft, yegMiddleX, yegMiddleY,
                 0, 0, DISPLAY_WIDTH - 60, DISPLAY_HEIGHT);

  // initial cursor position is the middle of the screen
  cursorX = (DISPLAY_WIDTH - 60)/2;
  cursorY = DISPLAY_HEIGHT/2;

  redrawCursor(TFT_RED);
}

void redrawCursor(uint16_t colour) {
  tft.fillRect(cursorX - CURSOR_SIZE/2, cursorY - CURSOR_SIZE/2,
               CURSOR_SIZE, CURSOR_SIZE, colour);
}

void processJoystick(){
  /* Move the cursor without leaving a trace behind it.
  Args: 
    None.
  Returns:
    None.
  */
  int xVal = analogRead(JOY_HORIZ);
  int yVal = analogRead(JOY_VERT);
  int buttonVal = digitalRead(JOY_SEL);
  int yegMiddleX = YEG_SIZE/2 - (DISPLAY_WIDTH - 60)/2;
  int yegMiddleY = YEG_SIZE/2 - DISPLAY_HEIGHT/2;
  int oldx = cursorX;
  int oldy = cursorY;

  if ((JOY_CENTER - yVal) > JOY_DEADZONE ){
    cursorY -= abs(JOY_CENTER - yVal)/100;
  }
  else if ((JOY_CENTER - yVal) < JOY_DEADZONE){
    cursorY += abs(JOY_CENTER - yVal)/100;
  }

  if ((JOY_CENTER - xVal) < JOY_DEADZONE){
    cursorX -= abs(JOY_CENTER - xVal)/100;
  }
  else if ((JOY_CENTER - xVal) > JOY_DEADZONE){
    cursorX += abs(JOY_CENTER - xVal)/100;
  }
  // 320 is the display height.
  if (constrain(cursorY - CURSOR_SIZE/2,0,320) == 0){
     cursorY = CURSOR_SIZE/2;
  }
  // Since we always start draw the cursor at the
  // left top corner so for the bottome and right edge, the
  // distance to the edge will be 9/2 + 1 = 5, for top and left
  // the distance will be 9/2 = 4. 
  else if (constrain(cursorY + CURSOR_SIZE/2,0,320) == 320){
    cursorY = 320-(CURSOR_SIZE/2+1);
  }
  // 420 is the display width(480) minus the black space(60).
  if (constrain(cursorX - CURSOR_SIZE/2,0,420) == 0){
    cursorX = CURSOR_SIZE/2;
  }
  else if (constrain(cursorX + CURSOR_SIZE/2,0,420) == 420){
    cursorX = 420-(CURSOR_SIZE/2+1);
  }
  int newx = cursorX;
  int newy = cursorY;
  if(oldx != newx || oldy != newy){
      lcd_image_draw(&yegImage, &tft,
        yegMiddleX + oldx - CURSOR_SIZE/2,
        yegMiddleY + oldy - CURSOR_SIZE/2,
        oldx - CURSOR_SIZE/2,
        oldy - CURSOR_SIZE/2, 
        CURSOR_SIZE, CURSOR_SIZE);
  }
  if(oldx != newx || oldy != newy){
    redrawCursor(TFT_RED);
  }

  delay(20);  
}

int main() {
	setup();

  while (true) {
    processJoystick();
  }

	Serial.end();
	return 0;
}
