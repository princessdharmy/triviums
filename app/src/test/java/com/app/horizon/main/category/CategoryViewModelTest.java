package com.app.horizon.main.category;

import com.app.horizon.screens.main.home.category.CategoryRepository;
import com.app.horizon.screens.main.home.category.CategoryViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link CategoryViewModelTest}.
 */
public class CategoryViewModelTest {

   /* @Mock
    private CategoryRepository categoryRepository;

    private CategoryViewModel categoryViewModel;

    @Before
    public void setUpCategoryViewModel(){
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        //Get a reference to the class under test
        categoryViewModel = new CategoryViewModel(categoryRepository);
    }

    @Test
    public void getCategoryFromRepository(){
        // Given an initialized CategoryViewModel with initialized category
        // When loading of Category is requested
        categoryViewModel.getCategory();

        //Callback is captured and invoked with stubbed categories
        verify(categoryRepository).getCategory();
    }*/
}
