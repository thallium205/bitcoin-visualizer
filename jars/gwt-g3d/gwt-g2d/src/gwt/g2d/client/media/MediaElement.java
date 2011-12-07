/*
 * Copyright 2009 Hao Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.g2d.client.media;

import com.google.gwt.dom.client.Element;

/**
 * Abstract element that supports media playback.
 * 
 * @author hao1300@gmail.com
 */
public abstract class MediaElement extends Element {

	protected MediaElement() {
		
	}
	
	/**
	 * Gets how confident the user agent is that it can play media resources of 
	 * the given type.
	 * 
	 * @param type
	 */
	public final Playability canPlayType(String type) {
		return Playability.parsePlayability(canPlayTypeImpl(type));
	}
	
	/**
	 * Returns a TimeRanges object that represents the ranges of the media 
	 * resource that the user agent has buffered.
	 */
	public native final TimeRanges getBuffered() /*-{
		return this.buffered;
	}-*/;
	
	/**
	 * Gets the address of the current media resource.
	 * 
	 * @return the empty string when there is no media resource.
	 */
	public native final String getCurrentSrc() /*-{
		return this.currentSrc;
	}-*/;

	/**
	 * Gets the current playback position, expressed in seconds.
	 */
	public native final float getCurrentTime() /*-{
		return this.currentTime;
	}-*/;
	
	/**
	 * Gets the desired speed at which the media resource is to play, as a 
	 * multiple of its intrinsic speed.
	 */
	public native final float getDefaultPlaybackRate() /*-{
		return this.defaultPlaybackRate;
	}-*/;
	
	/**
	 * Gets the length of the media resource, in seconds.
	 */
	public native final float getDuration() /*-{
		return this.duration;
	}-*/;
	
	/**
	 * Gets the code for the error.
	 */
	public final MediaError getError() {
		return MediaError.parseMediaError(getErrorImpl());
	}
	
	/**
	 * Gets the current network activity.
	 */
	public final NetworkState getNetworkState() {
		return NetworkState.parseNetworkState(getNetworkStateImpl());
	}
	
	/**
	 * Gets the speed at which the media resource plays, as a multiple of its 
	 * intrinsic speed.
	 */
	public native final float getPlaybackRate() /*-{
		return this.playbackRate;
	}-*/;
	
	/**
	 * Gets a new static normalized TimeRanges object that represents the ranges 
	 * of the media resource, if any, that the user agent has so far rendered, 
	 * at the time the attribute is evaluated.
	 */
	public native final TimeRanges getPlayed() /*-{
		return this.played;
	}-*/;
	
	/**
	 * Gets the current ready state of the media element.
	 */
	public final ReadyState getReadyState() {
		return ReadyState.parseReadyState(getReadyStateImpl());
	}
	
	/**
	 * Gets a new static normalized TimeRanges object that represents the ranges 
	 * of the media resource, if any, that the user agent is able to seek to, at 
	 * the time the attribute is evaluated.
	 */
	public native final TimeRanges getSeekable() /*-{
		return this.seekable;
	}-*/;
	
	/**
	 * Gets the address of the media resource (video, audio) to show.
	 */
	public native final String getSrc() /*-{
		return this.src;
	}-*/;
	
	/**
	 * Gets the earliest possible position, expressed in seconds.
	 */
	public native final float getStartTime() /*-{
		return this.startTime;
	}-*/;
	
	/**
	 * Gets the playback volume of any audio portions of the media element, in 
	 * the range 0.0 (silent) to 1.0 (loudest).
	 */
	public native final float getVolume() /*-{
		return this.volume;
	}-*/;
	
	/**
	 * Gets whether autobuffer is used.
	 */
	public native final boolean isAutobuffer() /*-{
		return this.autobuffer;
	}-*/;
	
	/**
	 * Gets whether the user agent will automatically begin playback of the media 
	 * resource as soon as it can do so without stopping. 
	 */
	public native final boolean isAutoplay() /*-{
		return this.autoplay;
	}-*/;
	
	/**
	 * Gets whether the user agent to provide its own set of controls.
	 */
  public native final boolean isControls() /*-{
		return this.controls;
	}-*/;
	
	/**
	 * Returns true if the media element has ended playback and the direction of 
	 * playback is forwards, and false otherwise.
	 */
	public native final boolean isEnded() /*-{
		return this.ended;
	}-*/;
	
	/**
	 * Gets whether the media element is to seek back to the start of the media 
	 * resource upon reaching the end.
	 */
	public native final boolean isLoop() /*-{
		return this.loop;
	}-*/;
	
	/**
	 * Returns true if the audio channels are muted and false otherwise.
	 */
	public native final boolean isMuted() /*-{
		return this.muted;
	}-*/;
	
	/**
	 * Gets whether the media element is paused or not. The attribute is 
	 * initially true.
	 */
	public native final boolean isPaused() /*-{
		return this.paused;
	}-*/;
	
	/**
	 * Returns true if the user agent is currently seeking.
	 */
	public native final boolean isSeeking() /*-{
		return this.seeking;
	}-*/;
	
	/**
	 * Causes the element to reset and start selecting and loading a new media 
	 * resource from scratch.
	 */
	public native final void load() /*-{
		this.load();
	}-*/;
	
	/**
	 * Sets the paused attribute to true, loading the media resource if necessary.
	 */
	public native final void pause() /*-{
		this.pause();
	}-*/;
	
	/**
	 * Sets the paused attribute to false, loading the media resource and 
	 * beginning playback if necessary. If the playback had ended, will restart 
	 * it from the start.
	 */
	public native final void play() /*-{
		this.play();
	}-*/;
	
	/**
	 * 
	 * Sets whether autobuffer is used.
	 * If true, hints to the user agent that the author believes that the media 
	 * element will likely be used, even though the element does not have an 
	 * autoplay attribute. This attribute may be ignored altogether. The 
	 * attribute must be ignored if the autoplay attribute is present.
	 * 
	 * @param autobuffer 
	 */
	public native final void setAutobuffer(boolean autobuffer) /*-{
		this.autobuffer = autobuffer;
	}-*/;
	
	/**
	 * If true, the user agent will automatically begin playback of the media 
	 * resource as soon as it can do so without stopping.
	 */
	public native final void setAutoplay(boolean autoplay) /*-{
		this.autoplay = autoplay;
	}-*/;
	
	/**
	 * If true, it indicates that the author has not provided a scripted 
	 * controller and would like the user agent to provide its own set of 
	 * controls.
	 */
  public native final void setControls(boolean controls) /*-{
		this.controls = controls;
	}-*/;
	
	/**
	 * Sets the current playback position, expressed in seconds.
	 */
	public native final void setCurrentTime(float currentTime) /*-{
		this.currentTime = currentTime;
	}-*/;
	
	/**
	 * Sets the desired speed at which the media resource is to play, as a 
	 * multiple of its intrinsic speed.
	 * 
	 * Default: 1
	 */
	public native final void setDefaultPlaybackRate(float defaultPlaybackRate) /*-{
		this.defaultPlaybackRate = defaultPlaybackRate;
	}-*/;
	
	/**
	 * If true, indicates that the media element is to seek back to the start of 
	 * the media resource upon reaching the end.
	 */
	public native final void setLoop(boolean loop) /*-{
		this.loop = loop;
	}-*/;
	
	/**
	 * If true, the audio channels are muted.
	 */
	public native final void setMuted(boolean muted) /*-{
		this.muted = muted;
	}-*/;
	
	/**
	 * Sets the speed at which the media resource plays, as a multiple of its 
	 * intrinsic speed. If it is not equal to the defaultPlaybackRate, then the 
	 * implication is that the user is using a feature such as fast forward or 
	 * slow motion playback.
	 * 
	 * Default: 1
	 */
	public native final void setPlaybackRate(float playbackRate) /*-{
		this.playbackRate = playbackRate;
	}-*/;
	
	/**
	 * Sets the address of the media resource (video, audio) to show.
	 * 
	 * @param src
	 */
	public native final void setSrc(String src) /*-{
		this.src = src;
	}-*/;
	
	/**
	 * Sets the playback volume of any audio portions of the media element, in 
	 * the range 0.0 (silent) to 1.0 (loudest).
	 * 
	 * @param volume
	 */
	public native final void setVolume(float volume) /*-{
		this.volume = volume;
	}-*/;

	/**
	 * Gets a string that describes how confident the user agent is that it can 
	 * play media resources of the given type.
	 * 
	 * @param type
	 */
	private native final String canPlayTypeImpl(String type) /*-{
		return this.canPlayType(type);
	}-*/;
	
	/**
	 * Gets the media code's error. Returns the error code, or 0 if no error.
	 */
	private native final int getErrorImpl() /*-{
		var error = this.error;
		if (!error) {
			return error.code;
		} else {
			return 0;
		}
	}-*/;
	
	/**
	 * Gets the code that describes the current network state.
	 */
	private final native int getNetworkStateImpl() /*-{
		return this.networkState;
	}-*/;
  
  /**
	 * Gets an integer value that describes the current ready state of the media 
	 * element.
	 */
	private native final int getReadyStateImpl() /*-{
		return this.readyState;
	}-*/;
}
