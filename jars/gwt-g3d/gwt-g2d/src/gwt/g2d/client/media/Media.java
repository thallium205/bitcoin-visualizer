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

import gwt.g2d.client.media.event.AbortEvent;
import gwt.g2d.client.media.event.AbortHandler;
import gwt.g2d.client.media.event.CanPlayEvent;
import gwt.g2d.client.media.event.CanPlayHandler;
import gwt.g2d.client.media.event.CanPlaythroughEvent;
import gwt.g2d.client.media.event.CanPlaythroughHandler;
import gwt.g2d.client.media.event.DurationChangeEvent;
import gwt.g2d.client.media.event.DurationChangeHandler;
import gwt.g2d.client.media.event.EmptiedEvent;
import gwt.g2d.client.media.event.EmptiedHandler;
import gwt.g2d.client.media.event.EndedEvent;
import gwt.g2d.client.media.event.EndedHandler;
import gwt.g2d.client.media.event.ErrorEvent;
import gwt.g2d.client.media.event.ErrorHandler;
import gwt.g2d.client.media.event.HasMediaEvents;
import gwt.g2d.client.media.event.LoadStartEvent;
import gwt.g2d.client.media.event.LoadStartHandler;
import gwt.g2d.client.media.event.LoadedDataEvent;
import gwt.g2d.client.media.event.LoadedDataHandler;
import gwt.g2d.client.media.event.LoadedMetadataEvent;
import gwt.g2d.client.media.event.LoadedMetadataHandler;
import gwt.g2d.client.media.event.PauseEvent;
import gwt.g2d.client.media.event.PauseHandler;
import gwt.g2d.client.media.event.PlayEvent;
import gwt.g2d.client.media.event.PlayHandler;
import gwt.g2d.client.media.event.PlayingEvent;
import gwt.g2d.client.media.event.PlayingHandler;
import gwt.g2d.client.media.event.ProgressEvent;
import gwt.g2d.client.media.event.ProgressHandler;
import gwt.g2d.client.media.event.RateChangeEvent;
import gwt.g2d.client.media.event.RateChangeHandler;
import gwt.g2d.client.media.event.SeekedEvent;
import gwt.g2d.client.media.event.SeekedHandler;
import gwt.g2d.client.media.event.SeekingEvent;
import gwt.g2d.client.media.event.SeekingHandler;
import gwt.g2d.client.media.event.StalledEvent;
import gwt.g2d.client.media.event.StalledHandler;
import gwt.g2d.client.media.event.SuspendEvent;
import gwt.g2d.client.media.event.SuspendHandler;
import gwt.g2d.client.media.event.TimeUpdateEvent;
import gwt.g2d.client.media.event.TimeUpdateHandler;
import gwt.g2d.client.media.event.VolumeChangeEvent;
import gwt.g2d.client.media.event.VolumeChangeHandler;
import gwt.g2d.client.media.event.WaitingEvent;
import gwt.g2d.client.media.event.WaitingHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * Represents a widget for manipulating a {@link MediaElement}.
 * 
 * @author hao1300@gmail.com
 */
public abstract class Media extends ComplexPanel implements HasMediaEvents {
	private List<Source> sources;
	private Set<String> registeredEventListeners;
	
	protected Media(MediaElement element) {
		setElement(element);
	}

	/**
	 * Adds a media source.
	 * 
	 * @param source
	 */
	public void addSource(Source source) {
		getSources().add(source);
		add(source, getElement());
	}
	
	/** 
	 * Gets all media sources.
	 */
	public List<Source> getSources() {
		if (sources == null) {
			sources = new ArrayList<Source>();
		}
		return sources;
	}
	
	@Override
	public HandlerRegistration addLoadStartHandler(LoadStartHandler handler) {
		return addCustomHandler(handler, LoadStartEvent.getType());
	}

	@Override
	public HandlerRegistration addProgressHandler(ProgressHandler handler) {
		return addCustomHandler(handler, ProgressEvent.getType());
	}

	@Override
	public HandlerRegistration addSuspendHandler(SuspendHandler handler) {
		return addCustomHandler(handler, SuspendEvent.getType());
	}

	@Override
	public HandlerRegistration addAbortHandler(AbortHandler handler) {
		return addCustomHandler(handler, AbortEvent.getType());
	}

	@Override
	public HandlerRegistration addErrorHandler(ErrorHandler handler) {
		return addCustomHandler(handler, ErrorEvent.getType());
	}

	@Override
	public HandlerRegistration addEmptiedHandler(EmptiedHandler handler) {
		return addCustomHandler(handler, EmptiedEvent.getType());
	}

	@Override
	public HandlerRegistration addStalledHandler(StalledHandler handler) {
		return addCustomHandler(handler, StalledEvent.getType());
	}

	@Override
	public HandlerRegistration addPlayHandler(PlayHandler handler) {
		return addCustomHandler(handler, PlayEvent.getType());
	}

	@Override
	public HandlerRegistration addPauseHandler(PauseHandler handler) {
		return addCustomHandler(handler, PauseEvent.getType());
	}

	@Override
	public HandlerRegistration addLoadedMetadataHandler(
			LoadedMetadataHandler handler) {
		return addCustomHandler(handler, LoadedMetadataEvent.getType());
	}

	@Override
	public HandlerRegistration addLoadedDataHandler(LoadedDataHandler handler) {
		return addCustomHandler(handler, LoadedDataEvent.getType());
	}

	@Override
	public HandlerRegistration addWaitingHandler(WaitingHandler handler) {
		return addCustomHandler(handler, WaitingEvent.getType());
	}

	@Override
	public HandlerRegistration addPlayingHandler(PlayingHandler handler) {
		return addCustomHandler(handler, PlayingEvent.getType());
	}

	@Override
	public HandlerRegistration addCanPlayHandler(CanPlayHandler handler) {
		return addCustomHandler(handler, CanPlayEvent.getType());
	}

	@Override
	public HandlerRegistration addCanPlaythroughHandler(
			CanPlaythroughHandler handler) {
		return addCustomHandler(handler, CanPlaythroughEvent.getType());
	}

	@Override
	public HandlerRegistration addSeekingHandler(SeekingHandler handler) {
		return addCustomHandler(handler, SeekingEvent.getType());
	}

	@Override
	public HandlerRegistration addSeekedHandler(SeekedHandler handler) {
		return addCustomHandler(handler, SeekedEvent.getType());
	}

	@Override
	public HandlerRegistration addTimeUpdateHandler(TimeUpdateHandler handler) {
		return addCustomHandler(handler, TimeUpdateEvent.getType());
	}

	@Override
	public HandlerRegistration addEndedHandler(EndedHandler handler) {
		return addCustomHandler(handler, EndedEvent.getType());
	}

	@Override
	public HandlerRegistration addRateChangeHandler(RateChangeHandler handler) {
		return addCustomHandler(handler, RateChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addDurationChangeHandler(
			DurationChangeHandler handler) {
		return addCustomHandler(handler, DurationChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addVolumeChangeHandler(VolumeChangeHandler handler) {
		return addCustomHandler(handler, VolumeChangeEvent.getType());
	}

	/**
	 * Gets how confident the user agent is that it can play media resources of
	 * the given type.
	 * 
	 * @param type
	 */
	public Playability canPlayType(String type) {
		return getMediaElement().canPlayType(type);
	}

	/**
	 * Returns a TimeRanges object that represents the ranges of the media
	 * resource that the user agent has buffered.
	 */
	public TimeRanges getBuffered() {
		return getMediaElement().getBuffered();
	}

	/**
	 * Gets the address of the current media resource.
	 * 
	 * @return the empty string when there is no media resource.
	 */
	public String getCurrentSrc() {
		return getMediaElement().getCurrentSrc();
	}

	/**
	 * Gets the current playback position, expressed in seconds.
	 */
	public float getCurrentTime() {
		return getMediaElement().getCurrentTime();
	}

	/**
	 * Gets the desired speed at which the media resource is to play, as a
	 * multiple of its intrinsic speed.
	 */
	public float getDefaultPlaybackRate() {
		return getMediaElement().getDefaultPlaybackRate();
	}

	/**
	 * Gets the length of the media resource, in seconds.
	 */
	public float getDuration() {
		return getMediaElement().getDuration();
	}

	/**
	 * Gets the code for the error.
	 */
	public MediaError getError() {
		return getMediaElement().getError();
	}

	/**
	 * Gets the current network activity.
	 */
	public NetworkState getNetworkState() {
		return getMediaElement().getNetworkState();
	}

	/**
	 * Gets the speed at which the media resource plays, as a multiple of its
	 * intrinsic speed.
	 */
	public float getPlaybackRate() {
		return getMediaElement().getPlaybackRate();
	}

	/**
	 * Gets a new static normalized TimeRanges object that represents the ranges
	 * of the media resource, if any, that the user agent has so far rendered, at
	 * the time the attribute is evaluated.
	 */
	public TimeRanges getPlayed() {
		return getMediaElement().getPlayed();
	}

	/**
	 * Gets the current ready state of the media element.
	 */
	public ReadyState getReadyState() {
		return getMediaElement().getReadyState();
	}

	/**
	 * Gets a new static normalized TimeRanges object that represents the ranges
	 * of the media resource, if any, that the user agent is able to seek to, at
	 * the time the attribute is evaluated.
	 */
	public TimeRanges getSeekable() {
		return getMediaElement().getSeekable();
	}

	/**
	 * Gets the address of the media resource (video, audio) to show.
	 */
	public String getSrc() {
		return getMediaElement().getSrc();
	}

	/**
	 * Gets the earliest possible position, expressed in seconds.
	 */
	public float getStartTime() {
		return getMediaElement().getStartTime();
	}

	/**
	 * Gets the playback volume of any audio portions of the media element, in the
	 * range 0.0 (silent) to 1.0 (loudest).
	 */
	public float getVolume() {
		return getMediaElement().getVolume();
	}

	/**
	 * Gets whether autobuffer is used.
	 */
	public boolean isAutobuffer() {
		return getMediaElement().isAutobuffer();
	}

	/**
	 * Gets whether the user agent will automatically begin playback of the media
	 * resource as soon as it can do so without stopping.
	 */
	public boolean isAutoplay() {
		return getMediaElement().isAutobuffer();
	}

	/**
	 * Gets whether the user agent to provide its own set of controls.
	 */
	public boolean isControls() {
		return getMediaElement().isControls();
	}

	/**
	 * Returns true if the media element has ended playback and the direction of
	 * playback is forwards, and false otherwise.
	 */
	public boolean isEnded() {
		return getMediaElement().isEnded();
	}

	/**
	 * Gets whether the media element is to seek back to the start of the media
	 * resource upon reaching the end.
	 */
	public boolean isLoop() {
		return getMediaElement().isLoop();
	}

	/**
	 * Returns true if the audio channels are muted and false otherwise.
	 */
	public boolean isMuted() {
		return getMediaElement().isMuted();
	}

	/**
	 * Gets whether the media element is paused or not. The attribute is initially
	 * true.
	 */
	public boolean isPaused() {
		return getMediaElement().isPaused();
	}

	/**
	 * Returns true if the user agent is currently seeking.
	 */
	public boolean isSeeking() {
		return getMediaElement().isSeeking();
	}

	/**
	 * Causes the element to reset and start selecting and loading a new media
	 * resource from scratch.
	 */
	public void load() {
		getMediaElement().load();
	}

	/**
	 * Sets the paused attribute to true, loading the media resource if necessary.
	 */
	public void pause() {
		getMediaElement().pause();
	}

	/**
	 * Sets the paused attribute to false, loading the media resource and
	 * beginning playback if necessary. If the playback had ended, will restart it
	 * from the start.
	 */
	public void play() {
		getMediaElement().play();
	}

	/**
	 * 
	 * Sets whether autobuffer is used. If true, hints to the user agent that the
	 * author believes that the media element will likely be used, even though the
	 * element does not have an autoplay attribute. getMediaElement() attribute
	 * may be ignored altogether. The attribute must be ignored if the autoplay
	 * attribute is present.
	 * 
	 * @param autobuffer
	 */
	public void setAutobuffer(boolean autobuffer) {
		getMediaElement().setAutobuffer(autobuffer);
	}

	/**
	 * If true, the user agent will automatically begin playback of the media
	 * resource as soon as it can do so without stopping.
	 */
	public void setAutoplay(boolean autoplay) {
		getMediaElement().setAutoplay(autoplay);
	}

	/**
	 * If true, it indicates that the author has not provided a scripted
	 * controller and would like the user agent to provide its own set of
	 * controls.
	 */
	public void setControls(boolean controls) {
		getMediaElement().setControls(controls);
	}

	/**
	 * Sets the current playback position, expressed in seconds.
	 */
	public void setCurrentTime(float currentTime) {
		getMediaElement().setCurrentTime(currentTime);
	}

	/**
	 * Sets the desired speed at which the media resource is to play, as a
	 * multiple of its intrinsic speed.
	 * 
	 * Default: 1
	 */
	public void setDefaultPlaybackRate(float defaultPlaybackRate) {
		getMediaElement().setDefaultPlaybackRate(defaultPlaybackRate);
	}

	/**
	 * If true, indicates that the media element is to seek back to the start of
	 * the media resource upon reaching the end.
	 */
	public void setLoop(boolean loop) {
		getMediaElement().setLoop(loop);
	}

	/**
	 * If true, the audio channels are muted.
	 */
	public void setMuted(boolean muted) {
		getMediaElement().setMuted(muted);
	}

	/**
	 * Sets the speed at which the media resource plays, as a multiple of its
	 * intrinsic speed. If it is not equal to the defaultPlaybackRate, then the
	 * implication is that the user is using a feature such as fast forward or
	 * slow motion playback.
	 * 
	 * Default: 1
	 */
	public void setPlaybackRate(float playbackRate) {
		getMediaElement().setPlaybackRate(playbackRate);
	}

	/**
	 * Sets the address of the media resource (video, audio) to show.
	 * 
	 * @param src
	 */
	public void setSrc(String src) {
		getMediaElement().setSrc(src);
	}

	/**
	 * Sets the playback volume of any audio portions of the media element, in the
	 * range 0.0 (silent) to 1.0 (loudest).
	 * 
	 * @param volume
	 */
	public void setVolume(float volume) {
		getMediaElement().setVolume(volume);
	}

	/**
	 * Gets the media element.
	 */
	protected MediaElement getMediaElement() {
		return getElement().cast();
	}

	/**
	 * Adds a custom event handler.
	 * 
	 * @param <H>
	 * @param handler
	 * @param type
	 */
	public <H extends EventHandler> HandlerRegistration addCustomHandler(
			H handler, DomEvent.Type<H> type) {
		if (registeredEventListeners == null) {
			registeredEventListeners = new HashSet<String>();
		} else if (registeredEventListeners.contains(type.getName())) {
			return addHandler(handler, type);
		}
		registerHandler(getElement(), type.getName(), handler);
		return addHandler(handler, type);
	}	
	
	/**
	 * Registers a customer event handler.
	 * 
	 * @param <H>
	 * @param elem
	 * @param eventName
	 * @param handler
	 */
	private native final <H extends EventHandler> void registerHandler(
			Element elem, String eventName, H handler) /*-{
		var widget = this; 
		elem.addEventListener(eventName, function(e) {
				@com.google.gwt.event.dom.client.DomEvent::fireNativeEvent(Lcom/google/gwt/dom/client/NativeEvent;Lcom/google/gwt/event/shared/HasHandlers;)
				(e, widget);
		}, false); 
	}-*/;
}
