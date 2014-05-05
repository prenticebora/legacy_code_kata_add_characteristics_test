package com.industriallogic.staticrecords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

public class SongPlayerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createSongPlayer() {
		String playerName = "player 1";
		SongPlayerStatistics statistics = new SongPlayerStatistics();

		SongPlayer songPlayer = new SongPlayer(playerName, statistics);

		assertNotNull(songPlayer);
		assertEquals(playerName, songPlayer.getPlayerName());
	}

	@Test
	public void playOneSong() {
		String playerName = "player 1";
		SongPlayerStatistics statistics = new SongPlayerStatistics();

		SongPlayer songPlayer = new SongPlayer(playerName, statistics);

		String songName = "song 1";
		int dataLength = 256;
		Song oneSong = new Song(songName, dataLength);
		songPlayer.addSong(oneSong);

		try {
			songPlayer.playSong(songName);
		} catch (SongNotFoundException e) {
			fail();
		}

		assertEquals(1, songPlayer.getNumberOfSongsPlayed());
	}

	@Test(expected = SongNotFoundException.class)
	public void playUnknownSong() throws SongNotFoundException {
		String playerName = "player 1";
		SongPlayerStatistics statistics = new SongPlayerStatistics();

		SongPlayer songPlayer = new SongPlayer(playerName, statistics);

		String unknownSongName = "unknown song";
		songPlayer.playSong(unknownSongName);
	}
}
