/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.codelabs.state.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

//    private var _todoItems = MutableLiveData(listOf<TodoItem>())
//    val todoItems: LiveData<List<TodoItem>> = _todoItems

    // mutableStateListOf<>()를 사용하면 observable한 mutableList를 만들 수 있다.
    // mutableList를 사용하여 작업하는 것과 같은 방식으로 리스트 조작 가능
    // LiveData<List>의 오버헤드를 줄일 수 있다.
    // 그러나 데이터가 view 시스템에서도 사용되고 있다면 liveData를 사용하는 것이 좋음.
    var todoItems = mutableStateListOf<TodoItem>()
    private set

    private var currentEditPosition by mutableStateOf(-1)
    val currentEditItem:TodoItem?
    get() = todoItems.getOrNull(currentEditPosition)

    fun addItem(item: TodoItem) {
//        _todoItems.value = _todoItems.value!! + listOf(item)
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
//        _todoItems.value = _todoItems.value!!.toMutableList().also {
//            it.remove(item)
//        }
        todoItems.remove(item)
        onEditDone()
    }

    fun onItemSelected(item:TodoItem){
        currentEditPosition = todoItems.indexOf(item)
    }

    fun onEditDone(){
        currentEditPosition = -1
    }

    fun onEditItemChange(item: TodoItem){
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id){
            error("difference item")
        }
        todoItems[currentEditPosition] = item
    }
}
