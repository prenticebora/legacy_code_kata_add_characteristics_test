package com.industriallogic.staticrecords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongPlayerStatistics {

	public Map<String, Song> songs = new HashMap<String, Song>();
	public int totalSongsPlayed = 0;
	public List<SongPlayer> songPlayers = new ArrayList<SongPlayer>();
	public int maxPlayersSoFar = 0;

	// player that plays the most songs
	public String getMostDemandingPlayer() {
		String result = null;
		int maxSoFar = 0;
		for (SongPlayer player : songPlayers)
			if (player.getNumberOfSongsPlayed() > maxSoFar) {
				maxSoFar = player.getNumberOfSongsPlayed();
				result = player.getPlayerName();
			}

		return result;
	}

	public int getMaximumSimultaneousPlayers() {
		if (getNumberSongPlayersActive() > maxPlayersSoFar)
			maxPlayersSoFar = getNumberSongPlayersActive();
		return maxPlayersSoFar;
	}

	public int getNumberSongPlayersActive() {
		return songPlayers.size();
	}

	// the song that most players have played.
	public String getMostPopularSong() {
		String result = null;
		int maxSoFar = 0;
		for (Song song : songs.values())
			if (song.getNumTimesPlayed() > maxSoFar) {
				maxSoFar = song.getNumTimesPlayed();
				result = song.name;
			}

		return result;
	}

	// statistics
	public int getTotalNumberSongsPlayed() {
		return totalSongsPlayed;
	}

	// statistics
	public int getTotalNumberOfSongs() {
		return songs.size();
	}

	public void addNewPlayer(SongPlayer songPlayer) {
		songPlayers.add(songPlayer);
	}

	public boolean containsSong(String songName) {
		return songs.containsKey(songName);
	}

	public void increaseTotalSongsPlayed() {
		++totalSongsPlayed;
	}

}
