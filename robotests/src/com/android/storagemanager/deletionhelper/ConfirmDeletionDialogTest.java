/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.storagemanager.deletionhelper;

import static com.google.common.truth.Truth.assertThat;

import android.R;
import android.app.Activity;
import android.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ConfirmDeletionDialogTest {
    @Test
    public void testOnCreateDialog_saysCorrectStrings() {
        final ConfirmDeletionDialog alertDialog = ConfirmDeletionDialog.newInstance(100L);
        startVisibleFragment(alertDialog);

        TextView message = alertDialog.getDialog().findViewById(R.id.message);
        Button button1 = alertDialog.getDialog().findViewById(android.R.id.button1);
        Button button2 = alertDialog.getDialog().findViewById(android.R.id.button2);
        assertThat(message.getText().toString())
                .isEqualTo("100 B of content will be removed from your device");
        assertThat(button1.getText().toString()).isEqualTo("Free up space");
        assertThat(button2.getText().toString()).isEqualTo("Cancel");
    }

    private static void startVisibleFragment(Fragment fragment) {
        Activity activity = Robolectric.setupActivity(Activity.class);
        activity
            .getFragmentManager()
            .beginTransaction()
            .add(android.R.id.content, fragment, null)
            .commitNow();
    }
}
