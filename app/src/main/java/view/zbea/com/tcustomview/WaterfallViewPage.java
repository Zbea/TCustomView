package view.zbea.com.tcustomview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;

import view.zbea.com.tcustomview.R;

/**
 * Created by Zbea on 16/2/24.
 */
public class WaterfallViewPage extends Activity
{

    private StaggeredGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_waterfall_view);
        initView();

    }

    private void initView()
    {
        mGridView = (StaggeredGridView) findViewById(R.id.grid);
        mGridView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return DATA.length;
        }

        @Override
        public Object getItem(int position) {
            return DATA[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(getApplicationContext());
            }
            TextView view = (TextView) convertView;
            view.setText(DATA[position]);
            view.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(-16777216)));
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.WHITE);
            return view;
        }

    }

    private static final String[] DATA = new String[] {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
            "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
            "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
            "Baylough", "Beaufort", "Beauvoorde"
    };

}
