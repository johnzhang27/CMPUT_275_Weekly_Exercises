/* --------------------------------------------------
*  Name: YongQuan Zhang
*  ID: 1515873
*  CMPUT 275, Winter 2020
*  Weekly Exercise #2: Restaurants and Pointers
*---------------------------------------------------*/

#include <Arduino.h>
#include <Adafruit_GFX.h>
#include <MCUFRIEND_kbv.h>
#include <SD.h>
#include <TouchScreen.h>

#define SD_CS 10

// physical dimensions of the tft display (# of pixels)
#define DISPLAY_WIDTH  480
#define DISPLAY_HEIGHT 320

// touch screen pins, obtained from the documentaion
#define YP A3 // must be an analog pin, use "An" notation!
#define XM A2 // must be an analog pin, use "An" notation!
#define YM 9  // can be a digital pin
#define XP 8  // can be a digital pin

// dimensions of the part allocated to the map display
#define MAP_DISP_WIDTH (DISPLAY_WIDTH - 60)
#define MAP_DISP_HEIGHT DISPLAY_HEIGHT

#define REST_START_BLOCK 4000000
#define NUM_RESTAURANTS 1066

// calibration data for the touch screen, obtained from documentation
// the minimum/maximum possible readings from the touch point
#define TS_MINX 100
#define TS_MINY 120
#define TS_MAXX 940
#define TS_MAXY 920

// thresholds to determine if there was a touch
#define MINPRESSURE   10
#define MAXPRESSURE 1000

MCUFRIEND_kbv tft;

// a multimeter reading says there are 300 ohms of resistance across the plate,
// so initialize with this to get more accurate readings
TouchScreen ts = TouchScreen(XP, YP, XM, YM, 300);

// different than SD
Sd2Card card;
uint32_t blockNum=0;
uint32_t oldblock=0;

struct restaurant {
  int32_t lat;
  int32_t lon;
  uint8_t rating; // from 0 to 10
  char name[55];
};
// Make this as a global variable, so we could access to the same
// 8 bytes on memory.
restaurant restBlock[8];

void setup() {
  init();
  Serial.begin(9600);

  // tft display initialization
  uint16_t ID = tft.readID();
  tft.begin(ID);
  // Draw the rectangle for buttons.
  tft.fillScreen(TFT_BLACK);
  tft.setRotation(1);
  tft.drawRect(DISPLAY_WIDTH-60,0,60,DISPLAY_HEIGHT/2,tft.color565(255,0,0));
  tft.drawRect(DISPLAY_WIDTH-60,DISPLAY_HEIGHT/2,60,DISPLAY_HEIGHT/2,tft.color565(255,0,0));
  tft.setCursor(0,0);
  tft.setTextColor(TFT_WHITE, TFT_BLACK);
  tft.setTextSize(2);
  // Display the correct message on the tft.
  tft.println("RECENT SLOW RUN:");
  tft.println("Not yet run");
  tft.println();
  tft.println("SLOW RUN AVG:");
  tft.println("Not yet run");
  tft.println();
  tft.println("RECENT FAST RUN:");
  tft.println("Not yet run");
  tft.println();
  tft.println("FAST RUN AVG:");
  tft.println("Not yet run");
  // Display the text for buttons.
  tft.drawChar(450,30,'S',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,50,'L',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,70,'O',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,90,'W',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,190,'F',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,210,'A',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,230,'S',TFT_WHITE,TFT_BLACK,2);
  tft.drawChar(450,250,'T',TFT_WHITE,TFT_BLACK,2);

  // SD card initialization for raw reads
  Serial.print("Initializing SPI communication for raw reads...");
  if (!card.init(SPI_HALF_SPEED, SD_CS)) {
    Serial.println("failed! Is the card inserted properly?");
    while (true) {}
  }
  else {
    Serial.println("OK!");
  }
}

// the implementation from class
void getRestaurant(int restIndex, restaurant* restPtr) {
  uint32_t blockNum = REST_START_BLOCK + restIndex/8;
  restaurant restBlock[8];

  while (!card.readBlock(blockNum, (uint8_t*) restBlock)) {
    Serial.println("Read block failed, trying again.");
  }

  *restPtr = restBlock[restIndex % 8];

}

void getRestaurantFast(int restIndex, restaurant* restPtr) {

  blockNum = REST_START_BLOCK + restIndex/8;
    if(blockNum != oldblock){
      while (!card.readBlock(blockNum, (uint8_t*) restBlock)) {
        Serial.println("Read block failed, trying again.");
      }
    }
  *restPtr = restBlock[restIndex % 8];
  oldblock = blockNum;

}
int processTOUCHSCREEN(){
  /* Determine the coordinates of touch. Then determine whether
    we are touching the buttons or somewhere else.
  Args: 
    None.
  Returns:
    0: if we don't have enough pressure or we touch the screen too hard.
    1: if we touch the SLOW button.
    2: if we touch the FAST button.
  */
  TSPoint touch = ts.getPoint();
  pinMode(YP, OUTPUT); 
  pinMode(XM, OUTPUT); 

  if (touch.z < MINPRESSURE || touch.z > MAXPRESSURE) {
    return 0;
  }
  int16_t screen_x = map(touch.y, TS_MINX, TS_MAXX, DISPLAY_WIDTH-1, 0);
  int16_t screen_y = map(touch.x, TS_MINY, TS_MAXY, DISPLAY_HEIGHT-1, 0);
  if (screen_x > 420 && screen_x < 480 && screen_y < 160){
    return 1;
  }
  else if (screen_x > 420 && screen_x < 480 && screen_y > 160){
    return 2;
  }
  delay(200);

}
// Update the display of SLOW part.
void SLOWwriteonTOUCHSCREEN(uint32_t time_slow2, uint32_t A_slow){
  tft.fillRect(0,0,420,90,TFT_BLACK);
  tft.setCursor(0,0);
  tft.setTextColor(TFT_WHITE, TFT_BLACK);
  tft.setTextSize(2);
  tft.println("RECENT SLOW RUN:");
  tft.print(time_slow2);
  tft.println(" ms");
  tft.println();
  tft.println("SLOW RUN AVG:");
  tft.print(A_slow);
  tft.println(" ms");
}
// Update the display of FAST part.
void FASTwriteonTOUCHSCREEN(uint32_t time_fast2, uint32_t A_fast){
  tft.fillRect(0,90,420,180,TFT_BLACK);
  tft.setCursor(0,95);
  tft.setTextColor(TFT_WHITE, TFT_BLACK);
  tft.setTextSize(2);
  tft.println("RECENT FAST RUN:");
  tft.print(time_fast2);
  tft.println(" ms");
  tft.println();
  tft.println("FAST RUN AVG:");
  tft.print(A_fast);
  tft.println(" ms");

}
int main() {
  uint32_t A_slow;
  uint32_t S_slow = 0;
  uint32_t n_slow = 0;
  uint32_t A_fast;
  uint32_t S_fast = 0;
  uint32_t n_fast = 0;
  restaurant rest;
  restaurant rest2;
  setup();
  while(true){
    int touch = processTOUCHSCREEN();
    // touch = 1 means we touch the SLOW button.
    if(touch == 1){
      uint32_t time_slow1 = millis();
      for(int i = 0;i < 1066; i++){
        getRestaurant(i, &rest);
      }
      uint32_t time_slow2 = millis();
      uint32_t time_slow = time_slow2-time_slow1;
      S_slow += time_slow;
      n_slow ++;
      A_slow = S_slow/n_slow;
      SLOWwriteonTOUCHSCREEN(time_slow,A_slow);
    }
    // Touch = 2 means we touch the fast button.
    else if(touch == 2){
      uint32_t time_fast1 = millis();
      for(int j = 0;j < 1066; j++){
        getRestaurantFast(j, &rest2);
      }
      uint32_t time_fast2 = millis();
      uint32_t time_fast = time_fast2-time_fast1;
      S_fast += time_fast;
      n_fast ++;
      A_fast = S_fast/n_fast;
      FASTwriteonTOUCHSCREEN(time_fast,A_fast);
    }
  }

  Serial.end();

  return 0;
}