/* 
 * Copyright (C) 2011 OpenIntents.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openintents.historify.data.model.source;

import org.openintents.historify.data.loaders.SourceIconHelper.IconLoadingStrategy;

/**
 * 
 * Model class representing an internal Source of events.
 * 
 * @author berke.andras
 */
public class InternalSource extends AbstractSource {

	public InternalSource(long id, String name, String description,
			String iconUri, IconLoadingStrategy iconLoadingStrategy, String authority, String eventIntent) {
		super(id, name, description, iconUri, iconLoadingStrategy, authority, eventIntent);
		mIsInternal = true;
	}

}
