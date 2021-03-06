/*
 * Copyright (C) 2017 CoorChice <icechen_@outlook.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * <p>
 * Last modified 17-4-17 下午7:41
 */

package com.zm.framegather.view.Adjuster;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.zm.framegather.view.SuperTextView;


/**
 * Project Name:SuperTextView
 * Author:CoorChice
 * Date:2017/4/17
 * Notes:
 */

public class OpportunityDemoAdjuster extends com.zm.framegather.view.SuperTextView.Adjuster {

  private float density;
  private Paint paint;


  public OpportunityDemoAdjuster() {
    initPaint();
  }

  private void initPaint() {
    paint = new Paint();
    paint.setAntiAlias(true);
  }



  @Override
  protected void adjust(SuperTextView v, Canvas canvas) {
    int width = v.getWidth();
    int height = v.getHeight();
    if (density == 0) {
      density = v.getResources().getDisplayMetrics().density;
    }
    paint.setColor(Color.rgb(63, 81, 181));
    canvas.drawCircle(width / 2, height / 2, 30 * density, paint);
  }
}
