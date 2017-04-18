# ParabolaBlast
## 抛物线爆炸小球
### 1.效果图(gif)：
![](https://github.com/xandone/ParabolaBlast/blob/master/pic/gif1.gif)
### 2.用法简介：
传入ParabolaView终点坐标(x,y),执行动画startAnim(x,y)</br>
当前Activity必须实现ParabolaView中的AnimEndInterface接口(已改)</br>
Activity实现AnimEndInterface太过于局限，更改成聚合的设计模式，适用于Activity/ViewGroup/Dialog等..
### 2.栗子：
#### 设置OnTouchListener事件，将手指点击处作为终点坐标(x,y)传入，开启动画效果。
```Java
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            parabolaView.startAnim((int) event.getX(), (int) event.getY());
        }
        return true;
    }
```
#### 重写:
```Java
 @Override
    public void onDrawBall(List<LittleBall> littleBalls) {
        boomView.startAnim(littleBalls);
    }
```
