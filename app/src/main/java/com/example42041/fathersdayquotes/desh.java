package com.example42041.fathersdayquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import link.fls.swipestack.SwipeStack;

public class desh extends AppCompatActivity implements SwipeStack.SwipeStackListener, View.OnClickListener {

    private Button mButtonLeft, mButtonRight;
    private FloatingActionButton mFab;
    private TextView txt;
    ImageView btnNxt,btnpre,share,copy,full,fav;
    CardView cardView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> mData;
    private SwipeStack mSwipeStack;
    private desh.SwipeStackAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // CardView card = (CardView)findViewById(R.id.card12);

        final String rQuote=getIntent().getStringExtra("quote");
        mSwipeStack = findViewById(R.id.swipeStack);
        mButtonLeft = findViewById(R.id.buttonSwipeLeft);
        mButtonRight = findViewById(R.id.buttonSwipeRight);
        mFab = findViewById(R.id.fabAdd);

        mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);
        mFab.setOnClickListener(this);
        String[] quote=getResources().getStringArray(R.array.all_quotes_english);
        mData = new ArrayList<>(Arrays.asList(quote));
        mAdapter = new desh.SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v.equals(mButtonLeft)) {
            mSwipeStack.swipeTopViewToLeft();
        } else if (v.equals(mButtonRight)) {
            mSwipeStack.swipeTopViewToRight();
        } else if (v.equals(mFab)) {
            Toast.makeText(this, "Refresh Quotes", Toast.LENGTH_SHORT).show();
            mSwipeStack.resetStack();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuReset:
                mSwipeStack.resetStack();
                Toast.makeText(this, "Refresh Quotes", Toast.LENGTH_SHORT).show();
                // Snackbar.make(mFab, R.string.stack_reset, Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.menuGitHub:
                Intent intent=new Intent(desh.this,favorite.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewSwipedToRight(int position) {
        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, getString(R.string.view_swiped_right, swipedElement),
//        Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, getString(R.string.view_swiped_left, swipedElement),
//        Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStackEmpty() {
        // Toast.makeText(this, R.string.stack_empty, Toast.LENGTH_SHORT).show();
        mSwipeStack.resetStack();
    }

    public class SwipeStackAdapter extends BaseAdapter {

        private List<String> mData;

        SwipeStackAdapter(List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
            }

            cardView = convertView.findViewById(R.id.card12);
            int[] androidColors = getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            cardView.setBackgroundColor(randomAndroidColor);
            cardView.setRadius(15);
            fav = convertView.findViewById(R.id.fav);copy = convertView.findViewById(R.id.copy);
            share = convertView.findViewById(R.id.share);full = convertView.findViewById(R.id.full);
            btnNxt = convertView.findViewById(R.id.buttonSwipeLeft1);
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent whatsPP = new Intent(Intent.ACTION_SEND );
                        whatsPP.setType("text/plain");
                        whatsPP.putExtra(Intent.EXTRA_TEXT,mData.get(position));
                        whatsPP.setPackage("com.whatsapp");
                        startActivity(whatsPP);
                    }catch (Exception e ){
                        e.printStackTrace();
                    }

                }
            });
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboard =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip= ClipData.newPlainText("editText",mData.get(position));
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(desh .this,"Copied", Toast.LENGTH_SHORT).show();
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(desh.this, "share", Toast.LENGTH_SHORT).show();
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,mData.get(position));
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, "Share by");
                    startActivity(shareIntent);
                }
            });
            full.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(desh.this, "full Screen", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(desh.this,desc.class);
                    String item=mData.get(position);
                    intent.putExtra("quote",item);
                    startActivity(intent);
                }
            });
            btnNxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(desh.this, "btn pre", Toast.LENGTH_SHORT).show();
                    mSwipeStack.swipeTopViewToLeft();
                }
            });
            btnpre = convertView.findViewById(R.id.buttonSwipeRight1);
            btnpre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(desh.this, "btn next", Toast.LENGTH_SHORT).show();
                    mSwipeStack.swipeTopViewToRight();
                }
            });
            TextView textViewCard = convertView.findViewById(R.id.textViewCard);
            textViewCard.setText(mData.get(position));

            return convertView;
        }


    }

}