package com.example.calculator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppsProvider {


    public static ArrayList<InstalledApps> getApps(Context context) {

        ArrayList<InstalledApps> res = new ArrayList<InstalledApps>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        final PackageItemInfo.DisplayNameComparator comparator = new PackageItemInfo.DisplayNameComparator(context.getPackageManager());

        Collections.sort(packs, new Comparator<PackageInfo>() {
            @Override
            public int compare(PackageInfo lhs, PackageInfo rhs) {
                return comparator.compare(lhs.applicationInfo, rhs.applicationInfo);
            }
        });
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

            InstalledApps newInfo = new InstalledApps();
            newInfo.label = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
            newInfo.pacakageName = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = String.valueOf(p.versionCode);


            newInfo.icon = p.applicationInfo.loadIcon(context.getPackageManager());
            res.add(newInfo);
        }


        //res.sort(String::compareToIgnoreCase);
        return res;


    }

    public static void unInstall(Activity context, String packageName) {
        /*Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:"+packageName));
        context.startActivity(intent);
        Toast.makeText(context,  packageName +"Uninstall Sucessfully..", Toast.LENGTH_SHORT).show();*/


        Intent intents = new Intent(Intent.ACTION_DELETE);
        intents.setData(Uri.parse("package:" + packageName));
        context.startActivity(intents);

    }

    public static void launchApp(Activity context, String packageName) {
        // or you can replace **'this'** with your **ActivityName.this**
        try {
            Intent i = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
