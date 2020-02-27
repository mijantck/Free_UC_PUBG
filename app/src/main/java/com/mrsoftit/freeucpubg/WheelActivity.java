package com.mrsoftit.freeucpubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import butterknife.Action;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WheelActivity extends AppCompatActivity {

    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("ads");

    FirebaseFirestore firestore;
    public TextView ucTextView;



    @BindView(R.id.spinBtn)
    Button spinBtn;
    @BindView(R.id.resultTv)
    TextView resultTv;
    @BindView(R.id.wheel)
    ImageView wheel;


    private static final int[] sectors = { 32, 15,
            19, 4, 21, 2, 25, 17, 34,
            6, 27,13, 36, 11, 30, 8,
            23, 10, 5, 24, 16, 33,
            1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35,
            3, 26, 0
    };


    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    // We have 37 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
   //   private static final float HALF_SECTOR = 360f / 37f / 2f;
     private static final float HALF_SECTOR = 4.86f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        ButterKnife.bind(this);


    }

        @OnClick(R.id.spinBtn)
        public void spin(View v) {
            degreeOld = degree % 360;
            // we calculate random angle for rotation of our wheel
            degree = RANDOM.nextInt(3600) + 720;
            // rotation effect on the center of the wheel
            RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(3600);
            rotateAnim.setFillAfter(true);
            rotateAnim.setInterpolator(new DecelerateInterpolator());
            rotateAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // we empty the result text view when the animation start
                    resultTv.setText("");
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    // we display the correct sector pointed by the triangle at the end of the rotate animation

                    int lukyUC = getSector(360 - (degree % 360));

                    ucUpdae(lukyUC);

                    resultTv.setText(lukyUC+"");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            // we start the animation
            wheel.startAnimation(rotateAnim);

    }

    private int getSector(int degrees) {
        int i = 0;
        int text =0;

        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

           /* Toast.makeText(this, degrees+" deg", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, start+" stat", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, end+"end ", Toast.LENGTH_SHORT).show();*/
            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = sectors[i];



            }
            i++;
            // now we can test our Android Roulette Game :)
            // That's all !
            // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
            // Subscribe and stay tuned !

        } while (text == 0  &&  i < sectors.length);





        return text;
    }

    public void ucUpdae(int i){

        if(i == 0 || i == 32 || i == 15 || i == 26 || i == 3){

            Toast.makeText(this, "1 ", Toast.LENGTH_SHORT).show();
        }
        if(i == 35 || i == 28 || i == 12 || i == 7 || i == 29){

            Toast.makeText(this, "0.50 ", Toast.LENGTH_SHORT).show();

        }
        if(i == 31 || i == 9 || i == 22 || i == 18 ){
            Toast.makeText(this, "0 ", Toast.LENGTH_SHORT).show();

        }
        if(i == 16 || i == 33 || i == 1 || i == 24 || i == 14){

            Toast.makeText(this, "0.15 ", Toast.LENGTH_SHORT).show();


        }if(i == 23 || i == 10 || i == 5 || i == 24 ){

            Toast.makeText(this, " .20 ", Toast.LENGTH_SHORT).show();


        }if(i == 13 || i == 36 || i == 11 || i == 30 || i == 8){

            Toast.makeText(this, "0.75 ", Toast.LENGTH_SHORT).show();


        }if(i == 25 || i == 17 || i == 34 || i == 6 || i == 27){

            Toast.makeText(this, "3 ", Toast.LENGTH_SHORT).show();

        }if(i == 15 || i == 19 || i == 4 || i == 21 || i == 2){

            Toast.makeText(this, "0.5 ", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onStart() {

        super.onStart();

        ucTextView = findViewById(R.id.ucTolal);

        firestore = FirebaseFirestore.getInstance();

        notebookRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AdsUcModel adsUcModel = document.toObject(AdsUcModel.class);
                        ucTextView.setText(adsUcModel.getUcAmount()+"");
                    }
                } else {

                }
            }
        });

    }

}
