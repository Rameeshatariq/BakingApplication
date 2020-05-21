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

package com.example.cv.baker_app.utlis;

import android.content.Context;

import com.example.cv.baker_app.AppExecutors;
import com.example.cv.baker_app.data.RecipesDatabase;
import com.example.cv.baker_app.data.RecipesRepository;
import com.example.cv.baker_app.ui_design.detail.IndicesViewModelFactory;
import com.example.cv.baker_app.ui_design.main.MainViewModelFactory;
import com.example.cv.baker_app.ui_design.desiredListingredients.DesiredIngredientViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for BakingApp.
 */
public class InjectorUtils {

    public static RecipesRepository provideRepository(Context context) {
        RecipesDatabase database = RecipesDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        BakingInterface bakingInterface = RetrofitClient.getClient().create(BakingInterface.class);
        return RecipesRepository.getInstance(database.recipeDao(), bakingInterface, executors);
    }

    public static MainViewModelFactory provideMainViewModelFactory(Context context) {
        RecipesRepository repository = provideRepository(context);
        return new MainViewModelFactory(repository);
    }

    public static IndicesViewModelFactory provideIndicesViewModelFactory(
            Context context, String recipeName) {
        RecipesRepository repository = provideRepository(context);
        return new IndicesViewModelFactory(repository, recipeName);
    }

    public static DesiredIngredientViewModelFactory provideListViewModelFactory(Context context) {
        RecipesRepository repository = provideRepository(context);
        return new DesiredIngredientViewModelFactory(repository);
    }
}
