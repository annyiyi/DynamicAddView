package com.example.dynamicaddview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btnAdd,btnGet;
	private OnClickListener l;
	private LinearLayout linearLayout;
	private ViewGroup viewgroup;
	private ArrayList<View> editViews = new ArrayList<View>();
	private ArrayList<String> strs = new ArrayList<String>();
	int i = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = new BtnLisener();
        initWedget();
		addView("");
    }
    
    private void initWedget(){
    	linearLayout = (LinearLayout) this.findViewById(R.id.LinearLayout1);
    	btnAdd = (Button) this.findViewById(R.id.btn_add);
    	btnGet = (Button) this.findViewById(R.id.btn_get);
    	btnAdd .setOnClickListener(l);
    	btnGet.setOnClickListener(l);
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    private void addView(String tex){

    	View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item, null);
    	Button btnDel = (Button) view.findViewById(R.id.btn_del);
    	final EditText text=(EditText)view.findViewById(R.id.editText1);
		EditText text1 = (EditText)view.findViewById(R.id.editText);
		text1.setText("leg"+i);
		view.setTag(text);
		text.setText(tex);

		EditText text2 = (EditText)view.findViewById(R.id.editText2);
		Button btAdd = (Button)view.findViewById(R.id.btn_add_view);
		if(i==1){
			btnDel.setVisibility(View.INVISIBLE);
		}else{
			btnDel.setVisibility(View.VISIBLE);
		}
    	btnDel.setTag(view);
		btnDel.setOnClickListener(l);
		btAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				String hh = text.getText().toString();
				System.out.println("-----"+hh);
				addView(hh);

//				Toast.makeText(MainActivity.this,"dd",Toast.LENGTH_LONG).show();
			}
		});
    	LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	linearLayout.addView(view, params);    
    	editViews.add(view);
		i = i + 1;


    	}
    
    private void removeView(View view){
    	linearLayout.removeView(view);
    	editViews.remove(view);
		i = i -1;
    }
    
    class BtnLisener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.btn_add:
				addView("");
				break;
			case R.id.btn_del:
				View view = (View) v.getTag();
				removeView(view);
				for(int i=0;i<editViews.size();i++){
					EditText text1=(EditText)editViews.get(i).findViewById(R.id.editText);
					text1.setText("leg"+(i+1));
				}
				break;
			case R.id.btn_get:
				System.out.println(editViews.size());
				for(int i=0;i<editViews.size();i++){
					EditText text1=(EditText)editViews.get(i).findViewById(R.id.editText1);
					EditText text2=(EditText)editViews.get(i).findViewById(R.id.editText2);
					System.out.println("----"+text1.getText());
					System.out.println("----"+text2.getText());
				}
				break;
			default:
				break;
			}
			
		}
    	
    }
    
}
