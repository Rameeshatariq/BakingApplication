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

package com.example.cv.baker_app.ui_design.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cv.baker_app.data.RecipesRepository;

import java.util.List;

/**
 * {@link ViewModel} for {@link IngredientsFragment}
 */
public class IndicesViewModel extends ViewModel {

    private final RecipesRepository mRepository;

    /** The list of ingredient indices which exist in the shopping list database */
    private LiveData<List<Integer>> mIndices;

    public IndicesViewModel(RecipesRepository repository, String recipeName) {
        mRepository = repository;
        mIndices = mRepository.getIndices(recipeName);
    }

    /**
     * Returns {@link LiveData} with the list of ingredient indices which exist in
     * the shopping list database.
     */
    public LiveData<List<Integer>> getIndices() {
        return mIndices;
    }
}
