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

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cv.baker_app.data.DesiredIngredientsListEntry;
import com.example.cv.baker_app.data.RecipesRepository;

import java.util.List;

/**
 * {@link ViewModel} for ShoppingActivity
 */
public class DesiredIngredientViewModel extends ViewModel {

    private final RecipesRepository mRepository;
    private final LiveData<List<DesiredIngredientsListEntry>> mList;

    public DesiredIngredientViewModel(RecipesRepository repository) {
        mRepository = repository;
        mList = mRepository.getAllShoppingList();
    }

    /**
     * Returns {@link LiveData} with the list of ShoppingListEntries
     */
    public LiveData<List<DesiredIngredientsListEntry>> getList() {
        return mList;
    }
}
