package com.industriallogic.staticrecords;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	}

}
