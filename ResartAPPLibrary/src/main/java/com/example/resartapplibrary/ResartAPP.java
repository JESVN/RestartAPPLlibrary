package com.example.resartapplibrary;
import android.app.Activity;
import android.content.Intent;

public class ResartAPP {
    public void restartApp() {
        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().finishAffinity();
        getActivity().startActivity(intent);
        System.exit(0);
        _unityActivity=null;
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
