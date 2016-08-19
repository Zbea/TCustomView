package view.zbea.com.tcustomview;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ExitApplication
{
	private List<Activity> activities=new LinkedList<Activity>();
	private static ExitApplication instance;
	
	/**
	 *  单里
	 * @return
	 */
	public static ExitApplication getInstance()
	{
		if (instance==null)
		{
			instance=new ExitApplication();
		}		
		return instance;		
	}
	
	 //添加activity
	 public void addActivity(Activity activity)
	 {
		 activities.add(activity);
	 }

	 //退出登录
	 public void exitApp()
	 {
		 for (Activity activity : activities)
		{
			activity.finish();			
		}
		 System.exit(0);
		 	 
	 }
	
}
