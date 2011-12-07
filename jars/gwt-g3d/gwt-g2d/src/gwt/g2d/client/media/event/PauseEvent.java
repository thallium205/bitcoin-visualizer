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
package gwt.g2d.client.media.event;

import com.google.gwt.event.dom.client.DomEvent;

/**
 * Represents a native pause event.
 *
 * @author hao1300@gmail.com
 */
public class PauseEvent extends DomEvent<PauseHandler> {

  /**
   * Event type for pause events. Represents the meta-data associated with this
   * event.
   */
  private static final Type<PauseHandler> TYPE = new Type<PauseHandler>(
      "pause", new PauseEvent());

  /**
   * Gets the event type associated with pause events.
   * 
   * @return the handler type
   */
  public static Type<PauseHandler> getType() {
    return TYPE;
  }

  /**
   * Protected constructor, use
   * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, com.google.gwt.event.shared.HasHandlers)}
   * to fire error events.
   */
  protected PauseEvent() {
  }

  @Override
  public final Type<PauseHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PauseHandler handler) {
    handler.onPause(this);
  }
}
