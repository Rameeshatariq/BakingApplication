/*
 *  Copyright 2018 Soojeong Shin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.cv.baker_app.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.cv.baker_app.utlis.Constant;

import timber.log.Timber;

/**
 * {@link RecipesDatabase} database for the application including a table for {@link DesiredIngredientsListEntry}
 * with the DAO {@link RecipeDao}
 */

// List of the entry class and associated TypeConverters
@Database(entities = {DesiredIngredientsListEntry.class}, version = 1, exportSchema = false)
public abstract class RecipesDatabase extends RoomDatabase {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static RecipesDatabase sInstance;

    public static RecipesDatabase getInstance(Context context) {
        Timber.d("Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        RecipesDatabase.class, Constant.DATABASE_NAME).build();
                Timber.d("Creating new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract RecipeDao recipeDao();
}
