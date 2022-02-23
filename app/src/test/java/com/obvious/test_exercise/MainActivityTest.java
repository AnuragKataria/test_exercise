package com.obvious.test_exercise;

import static org.junit.Assert.assertTrue;

import com.obvious.test_exercise.model.DataModel;
import com.obvious.test_exercise.repository.ImagesRespository;
import com.obvious.test_exercise.utils.ResourceHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class MainActivityTest {
    // This method will check the Json string in Asset folder contains data or not
    @Test
    public void isJsonStringIsEmptyOrNot() {
        final String jsonString = ResourceHelper.loadString("test_data/data.json");
        assertTrue(jsonString != null);
    }

    // This method will check the Arraylist which we are setting in adapter for recyclerview
    // have data or empty
    @Test
    public void checkArrayListEmptyOrNot() {
        final String jsonString = ResourceHelper.loadString("test_data/data.json");
        ImagesRespository imagesRespository = new ImagesRespository();
        ArrayList<DataModel> imagesArrayList = imagesRespository.getImagesArrayListTest(jsonString);
        assertTrue(imagesArrayList.size() > 0);
    }

}