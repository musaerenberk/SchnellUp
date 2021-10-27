package com.mebk.schnellup;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class schnellUp extends ApplicationAdapter {
	Music music;
	SpriteBatch batch;
	Texture background;
	Texture buton;

	Texture bird;
	float birdX = 0;
	float birdY = 0;
	int gameState = 0;
	float velocity = 0;
	float gravity = 0.1f;
	Texture dusman1;
	Texture dusman2;
	Texture dusman3;
	Texture giris;
	Texture giris2;
	Texture giris3;

	int dusmanSetiSayim =4;
	float[] dusmanX = new float[dusmanSetiSayim];

	float distance =0;
	float dusmanHizi=12.2f;
	Random random;
	float[] enemyOffSet = new float[dusmanSetiSayim];
	float[] enemyOffSet2 = new float[dusmanSetiSayim];
	float[] enemyOffSet3 = new float[dusmanSetiSayim];
	Circle birdCircle;
	Circle[] enemyCircles;
	Circle[] enemyCircles2;
	Circle[] enemyCircles3;
	int score=0;
	int scoredDusman=0;
	BitmapFont font;
	BitmapFont font2;

	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		batch =	new SpriteBatch();
		background = new Texture("arkaplan.png");
		buton = new Texture("playbtn.png");
		giris = new Texture("giris.png");
		giris2 = new Texture("giris2.png");
		giris3 = new Texture("giris3.png");

		bird = new Texture("biz.png");
		birdX = Gdx.graphics.getWidth() / 9;
		birdY = Gdx.graphics.getHeight() / 3;
		dusman1 = new Texture("dusman.png");
		dusman2 = new Texture("dusman.png");
		dusman3 = new Texture("dusman.png");
		distance =Gdx.graphics.getWidth() /2;
		random = new Random();
		birdCircle = new Circle();
		enemyCircles = new Circle[dusmanSetiSayim];
		enemyCircles2 = new Circle[dusmanSetiSayim];
		enemyCircles3 = new Circle[dusmanSetiSayim];

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);
		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(3);
		for (int i=0; i<dusmanSetiSayim; i++) {

			enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f)* (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

			dusmanX[i] = Gdx.graphics.getWidth() + i * distance;

			enemyCircles[i]=new Circle();
			enemyCircles2[i]=new Circle();
			enemyCircles3[i]=new Circle();


		}

	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		if (gameState == 1) {

			if (dusmanX[scoredDusman] < Gdx.graphics.getWidth() / 9) {
				score++;

				if (scoredDusman < dusmanSetiSayim - 1) {
					scoredDusman++;

				} else {
					scoredDusman = 0;
				}

			}if (score>-1 && score<7){
				dusmanHizi=12f;

			}else if (score>7 && score<12){
				dusmanHizi=14f;

			}else if (score>12 && score<16){
				dusmanHizi=6.5f;

			}else if (score>16 && score<25){
				dusmanHizi=15.5f;

			}else if (score>25 && score<27){
				dusmanHizi=5f;

			}else if (score>27 && score<35){
				dusmanHizi=14f;

			}else if (score>35 && score<44){
				dusmanHizi=20f;

			}else if (score>44 && score<65){
				dusmanHizi=22f;

			}else if (score>65 && score<100){
				dusmanHizi=25f;

			}else if (score>100 && score<102){
				dusmanHizi=2f;

			}else if (score>102 && score<120){
				dusmanHizi=25f;

			}else if (score>120){
				dusmanHizi=35f;

			}
			batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 8);
			font.draw(batch,String.valueOf(score),100,200);

			for (int i = 0; i < dusmanSetiSayim; i++) {

				if (dusmanX[i] < Gdx.graphics.getWidth() / 15) {

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

					dusmanX[i] = dusmanX[i] + dusmanSetiSayim * distance;

				} else {

					dusmanX[i] -= dusmanHizi;
				}


				batch.draw(dusman1, dusmanX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
				batch.draw(dusman2, dusmanX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
				batch.draw(dusman3, dusmanX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);

				enemyCircles[i] = new Circle(dusmanX[i] + Gdx.graphics.getWidth() / 35, Gdx.graphics.getHeight() / 2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 25, Gdx.graphics.getWidth() / 47);
				enemyCircles2[i] = new Circle(dusmanX[i] + Gdx.graphics.getWidth() / 35, Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 25, Gdx.graphics.getWidth() / 47);
				enemyCircles3[i] = new Circle(dusmanX[i] + Gdx.graphics.getWidth() / 35, Gdx.graphics.getHeight() / 2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 25, Gdx.graphics.getWidth() / 47);
			}


			if (Gdx.input.justTouched()) {
				velocity = -5;

			}


			if (birdY > 0) {
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}else{
				gameState=2;
			}
			if (birdY < (Gdx.graphics.getHeight()-85)){
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}else{
				gameState=2;
			}

		} else if (gameState ==0) {
			batch.draw(buton, Gdx.graphics.getWidth() / (5.2f), Gdx.graphics.getHeight() / (1.38f), Gdx.graphics.getWidth() /13, Gdx.graphics.getHeight() / 6);
			batch.draw(bird, Gdx.graphics.getWidth() / (16f), Gdx.graphics.getHeight() / (12f), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / (1.5f));
			batch.draw(giris, Gdx.graphics.getWidth()/(1.3f), Gdx.graphics.getHeight() / (4.5f), Gdx.graphics.getWidth() / 13, Gdx.graphics.getHeight() / 6);
			batch.draw(giris2, Gdx.graphics.getWidth()/(1.37f), Gdx.graphics.getHeight() / (1.9f), Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() / (3.5f));
			batch.draw(giris3, Gdx.graphics.getWidth()/(1.45f), Gdx.graphics.getHeight() / (2.5f), Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 8);

			if (Gdx.input.justTouched()) {
				gameState = 1;

			}

		}
		else if (gameState ==2) {
			font2.draw(batch,"schnellmet",Gdx.graphics.getWidth()/(1.65f),Gdx.graphics.getWidth()/(26));
			batch.draw(buton, Gdx.graphics.getWidth() / (5.2f), Gdx.graphics.getHeight() / (1.38f), Gdx.graphics.getWidth() /13, Gdx.graphics.getHeight() / 6);
			batch.draw(bird, Gdx.graphics.getWidth() / (16f), Gdx.graphics.getHeight() / (12f), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / (1.5f));
			batch.draw(giris, Gdx.graphics.getWidth()/(1.3f), Gdx.graphics.getHeight() / (4.5f), Gdx.graphics.getWidth() / 13, Gdx.graphics.getHeight() / 6);
			batch.draw(giris2, Gdx.graphics.getWidth()/(1.37f), Gdx.graphics.getHeight() / (1.9f), Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() / (3.5f));
			batch.draw(giris3, Gdx.graphics.getWidth()/(1.45f), Gdx.graphics.getHeight() / (2.5f), Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 8);
			if (Gdx.input.justTouched()) {
				gameState = 1;

				birdY = Gdx.graphics.getHeight() / 3;

				for (int i=0; i<dusmanSetiSayim; i++) {

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f)* (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

					dusmanX[i] = Gdx.graphics.getWidth() + i * distance;

					enemyCircles[i]=new Circle();
					enemyCircles2[i]=new Circle();
					enemyCircles3[i]=new Circle();

				}velocity=0; scoredDusman=0; score=0;

			}
		}


		batch.end();

		birdCircle.set(birdX + Gdx.graphics.getWidth() / 28, birdY + Gdx.graphics.getHeight() / (16.5f), Gdx.graphics.getWidth() / 36);





		for (int i = 0; i < dusmanSetiSayim; i++) {


			if (Intersector.overlaps(birdCircle, enemyCircles[i]) || Intersector.overlaps(birdCircle, enemyCircles2[i]) || Intersector.overlaps(birdCircle, enemyCircles3[i])) {
				gameState = 2;


			}
		}


	}
	@Override
	public void dispose () {

	}
}