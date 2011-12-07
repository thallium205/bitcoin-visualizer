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

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link WaitingEvent} events.
 *
 * @author hao1300@gmail.com
 */
public interface WaitingHandler extends EventHandler {

  /**
   * Called when WaitingEvent is fired.
   * 
   * @param event the {@link WaitingEvent} that was fired
   */
  void onWaiting(WaitingEvent event);
}