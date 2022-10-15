package com.example.purodhikasharma_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Exercise01 extends AppCompatActivity {
    private ImageView reusableImageView;

    // coordinates for line movement
    private int startx = 10;
    private int starty = 10;
    private int endx = 10;
    private int endy = 10;

    // canvass variables
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    //x and y coordinate
    private TextView xCoord;
    private TextView yCoord;

    // line properties
    private String lineColor = "";
    private int strokeWidth = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise01);


                paint = new Paint();
                paint.setStrokeWidth(strokeWidth);

                // creating a bitmap for the image
                bitmap = Bitmap.createBitmap((int) getWindowManager()
                        .getDefaultDisplay().getWidth(), (int) getWindowManager()
                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

                canvas = new Canvas(bitmap);
                // set the background for your drawings
                canvas.drawColor(Color.BLACK);


                reusableImageView = (ImageView)findViewById(R.id.viewForDrawing);

                reusableImageView.setImageBitmap(bitmap);
                reusableImageView.setVisibility(View.VISIBLE);

                xCoord= (TextView) findViewById(R.id.xCoordInput);
                yCoord = (TextView) findViewById(R.id.yCoordInput);

                // line color selection
                setupLineColor();
            }

            public void clearCanvas(View v)
            {
                // reset the background
                canvas.drawColor(Color.BLACK);

                // reset the coordinates
                startx = 10;
                starty = 10;
                endx = 10;
                endy = 10;

                // reset the edit text
                xCoord.setText("");
                yCoord.setText("");
            }

            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event) {


                if (!isArrowKeys(keyCode)) {
                    return super.onKeyDown(keyCode, event);
                }


                switch(keyCode) {
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        processLineMovement(LineMovement.DOWN);
                        return true;
                    case KeyEvent.KEYCODE_DPAD_UP:
                        processLineMovement(LineMovement.UP);
                        return true;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        processLineMovement(LineMovement.RIGHT);
                        return true;
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        processLineMovement(LineMovement.LEFT);
                        return true;
                }

                return false;
            }

            /* handlers for the movement*/
            public void moveDown(View view) {
                processLineMovement(LineMovement.DOWN);
            }

            public void moveUp(View view) {
                processLineMovement(LineMovement.UP);
            }

            public void moveRight(View view) {
                processLineMovement(LineMovement.RIGHT);
            }

            public void moveLeft(View view) {
                processLineMovement(LineMovement.LEFT);
            }


            private boolean isArrowKeys(int keyCode) {
                boolean retVal = false;

                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN ||
                        keyCode == KeyEvent.KEYCODE_DPAD_UP ||
                        keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ||
                        keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                    retVal = true;
                }

                return retVal;
            }

//            private void checkLineThickness() {
//                Spinner lineThicknessSpinner = (Spinner) findViewById(R.id.lineThicknessSpinner);
//                int strokeWidthValue = Integer.parseInt(lineThicknessSpinner.getSelectedItem().toString());
//                if (strokeWidth != strokeWidthValue) {
//                    paint.setStrokeWidth(strokeWidthValue);
//                }
//            }

            private void processLineMovement(LineMovement lineMovement) {
                String xCoordStr = xCoord.getText().toString();
                String yCoordStr = yCoord.getText().toString();

                // handle the x value from edit view
                if (!xCoordStr.isEmpty()) {
                    int xFromEditView = Integer.parseInt(xCoord.getText().toString());

                    if (xFromEditView <= 10) {
                        startx = 10;
                        endx = 10;
                    }

                    if (xFromEditView != startx) {
                        startx = xFromEditView;
                        endx = xFromEditView;
                    }
                }

                // handle the y value from edit view
                if (!yCoordStr.isEmpty()) {
                    int yEditView = Integer.parseInt(yCoord.getText().toString());

                    if (yEditView <= 10) {
                        starty = 10;
                        endy = 10;
                    }

                    if (yEditView != starty) {
                        starty = yEditView;
                        endy = yEditView;
                    }
                }

                switch(lineMovement) {
                    case DOWN:
                        endy = endy + 5;
                        drawLine(canvas);
                        break;
                    case UP:
                        int yMinus = endy - 5;
                        if (endy <= 10) {
                            endy = 10;
                        } else {
                            endy = yMinus;
                        }
                        drawLine(canvas);
                        break;
                    case RIGHT:
                        endx = endx + 5;

                        drawLine(canvas);
                        break;
                    case LEFT:
                        int xMinus = endx - 5;
                        if (xMinus <= 10) {
                            endx = 10;
                        } else {
                            endx = endx - 5;
                        }

                        drawLine(canvas);
                        break;
                }
            }

            private void drawLine(Canvas canvas)
            {
                xCoord.setText(String.valueOf(endx));
                yCoord.setText(String.valueOf(endy));

                canvas.drawLine(startx, starty, endx, endy, paint);
                startx = endx;
                starty = endy;

                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                reusableImageView.invalidate();
            }

            private void setupLineColor() {
                // create the radio group
                RadioGroup lineColorGroup = findViewById(R.id.lineColorGroup);
                lineColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rbtnRed = findViewById(R.id.redRadioButton);
                        RadioButton rbtnGreen = findViewById(R.id.greenRadioButton);

                        if (rbtnRed.isChecked()) {
                            lineColor = "RED";
                            paint.setColor(Color.RED);
                        } else if (rbtnGreen.isChecked()){
                            lineColor = "GREEN";
                            paint.setColor(Color.GREEN);
                        } else {
                            lineColor = "YELLOW";
                            paint.setColor(Color.YELLOW);
                        }
                    }
                });
            }
        }
