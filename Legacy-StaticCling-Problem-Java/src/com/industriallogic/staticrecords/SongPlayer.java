/// ***************************************************************************
/// Copyright (c) 2010, Industrial Logic, Inc., All Rights Reserved.
///
/// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
/// used by students during Industrial Logic's workshops or by individuals
/// who are being coached by Industrial Logic on a project.
///
/// This code may NOT be copied or used for any other purpose without the prior
/// written consent of Industrial Logic, Inc.
/// ****************************************************************************

package com.industriallogic.staticrecords;

public class SongPlayer {
	private SongPlayerStatistics songPlayerStatistics;
	private String playerName;
	private int numberOfSongsPlayed;

	public SongPlayer(String playerName, SongPlayerStatistics statistics) {
		this.playerName = playerName;
		numberOfSongsPlayed = 0;
		songPlayerStatistics = statistics;

		songPlayerStatistics.addNewPlayer(this);
	}

	public void playSong(String songName) throws SongNotFoundException {
		// omitting code for running on a separate thread, with mutexes to
		// protect
		// instance and static variables, etc.

		if (!songPlayerStatistics.containsSong(songName)) {
			throw new SongNotFoundException(songName);
		}

		Song theSong = songPlayerStatistics.songs.get(songName);

		startStream(theSong.name);

		playStream(theSong);

		stopStream();

		updateStatistics(theSong);
	}

	private void playStream(Song theSong) {
		int networkSegmentSize = 100;
		int offset = 0;

		do {
			int segmentSize = networkSegmentSize;
			if (segmentSize
					+ offset > theSong.dataLength) {
				segmentSize = theSong.dataLength
						- offset;
			}
			
			streamData(theSong.getData(), offset, segmentSize);
			
			offset += networkSegmentSize;
		} while (offset < theSong.dataLength);
	}

	private void updateStatistics(Song theSong) {
		songPlayerStatistics.getMaximumSimultaneousPlayers();

		songPlayerStatistics.increaseTotalSongsPlayed();

		++numberOfSongsPlayed;

		theSong.incNumTimesPlayed();
	}

	public void addSong(Song song) {
		if (!songPlayerStatistics.songs.containsValue(song)) {
			songPlayerStatistics.songs.put(song.name, song);
		}
	}

	private void startStream(String songName) {
		// send song info to client, including songName
	}

	private void streamData(byte[] data, int from, int dataSegmentSize) {
		// send a part of the song's binary data to the client
	}

	private void stopStream() {
		// tell the song client to end the song playback
	}

	public SongPlayerStatistics getSongPlayerStatistics() {
		return songPlayerStatistics;
	}

	public void setSongPlayerStatistics(
			SongPlayerStatistics songPlayerStatistics) {
		this.songPlayerStatistics = songPlayerStatistics;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getNumberOfSongsPlayed() {
		return numberOfSongsPlayed;
	}

}

class SongNotFoundException extends Exception {
	SongNotFoundException(String songName) {
		super("Song not found: '"
				+ songName + "'");
	}
}
