package com.example.restartlibrary;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class AppRestart {

    public void restartApplication(int delay) {
        //android 重启
//        final Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        getActivity().startActivity(intent);

        // unity 重启
        Log.d("Unity", "========restart "+delay);

        Intent restartIntent = getActivity().getPackageManager()
                .getLaunchIntentForPackage(getActivity().getPackageName() );
        PendingIntent intent = PendingIntent.getActivity(getActivity(), 0,restartIntent,0);
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.ELAPSED_REALTIME, 1000, intent);
        getActivity().finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    // 设置一个 Activity 参数

    private Activity _unityActivity;

    // 通过反射获取 Unity 的 Activity 的上下文

    Activity getActivity(){

        if(null == _unityActivity){

            try{

                Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");

                Activity activity = (Activity) classtype.getDeclaredField("currentActivity").get(classtype);

                _unityActivity = activity;

            }catch (ClassNotFoundException e){

                e.printStackTrace();

            }catch (IllegalAccessException e){

                e.printStackTrace();

            }catch (NoSuchFieldException e){

                e.printStackTrace();

            }

        }

        return _unityActivity;

    }
}