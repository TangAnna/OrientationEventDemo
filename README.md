# OrientationEventDemo
###Android 根据OrientationEventListener获取设备的方向

做有关视频的项目的时候会遇到横竖屏的问题，有很多种方法实现，OrientationEventListener是其中的一种方法

最近做了一个小视频录制的模块，录制视频时要根据录制时屏幕的方向确认录制出来的图像是什么方向的，一开始使用的方式是：<br/>重写

    @Override
          public void onConfigurationChanged(Configuration newConfig) {
              if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                  Log.d(TAG, "onConfigurationChanged: "+"竖屏"); 
              }else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
                  Log.d(TAG, "onConfigurationChanged: "+"竖屏");
              }
          }
<br/>在Manifest文件中对应的activity添加配置
<br/>   `android:configChanges="orientation|screenSize|keyboardHidden"`
<br/>但是一点都不起作用，不管屏幕怎么旋转都不会调用onConfigurationChanged()方法,找了一些解决的办法但是还是解决不了(有大神知道为什么，希望能告诉一下，谢谢！),最后就换成了使用OrientationEventListener来监听设备的变化来设置画面的方向，还挺好用的。
<br/>用起来也是挺简单的

    orientationEventListener = new OrientationEventListener(this) {
                    @Override
                    public void onOrientationChanged(int orientation) {
                        Log.d(TAG, "onOrientationChanged: " + orientation);
                        if (orientation > 350 || orientation < 20) { //0度  90 正竖屏

                        } else if (orientation > 70 && orientation < 110) { //90度 右横屏

                        } else if (orientation > 160 && orientation < 200) { //180度 倒竖屏

                        } else if (orientation > 250 && orientation < 290) { //270度 左横屏

                        } 
                    }
                };
                if (mOrientationEventListener.canDetectOrientation()) {//判断设备是否支持
                       mOrientationEventListener.enable();
                } else {
                       mOrientationEventListener.disable();//注销
                       mTvAngle.setText("当前设备不支持手机旋转！");
                }
<br/>得到了orientation，可以根据自己的需要判断设备的角度确定横竖屏，要记得在onDestroy()方法里面注销监听以免费电mOrientationEventListener.enable();
<br/>_(此Demo是记录自己工作中的一些问题，如发现错误的地方请指教，谢谢!
<br/>                                                                                   --（TangAnna   QQ:1101870076）)_
    


