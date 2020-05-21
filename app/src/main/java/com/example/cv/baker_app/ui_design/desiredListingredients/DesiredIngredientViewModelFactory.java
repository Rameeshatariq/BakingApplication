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

package com.example.cv.baker_app.ui_design.desiredListingredients;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cv.baker_app.data.RecipesRepository;

/**
 *  Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link RecipesRepository}
 */
public class DesiredIngredientViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final RecipesRepository mRepository;

    public DesiredIngredientViewModelFactory(RecipesRepository repository) {
        mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DesiredIngredientViewModel(mRepository);
    }
}
