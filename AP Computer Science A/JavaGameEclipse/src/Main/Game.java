package Main;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUId = -240840600533728354L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	private Random r;
	private Handler handler;

	public Game() {
		handler = new Handler();
		 
		new Window(WIDTH, HEIGHT, "LETS MAKE GAMES", this);
		

		r = new Random();

		handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));

	}

	public synchronized void start() {
		thread = new Thread(this); // initializing thread as a new thread
		thread.start(); // starts the thread
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join(); // this statement stops the game from running
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game(); // creates a new instance of the game
	}
}
