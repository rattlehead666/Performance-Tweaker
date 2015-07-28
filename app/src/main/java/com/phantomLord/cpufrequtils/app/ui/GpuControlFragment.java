package com.phantomLord.cpufrequtils.app.ui;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import com.phantomLord.cpufrequtils.app.R;
import com.phantomLord.cpufrequtils.app.utils.CpuFrequencyUtils;
import com.phantomLord.cpufrequtils.app.utils.GpuUtils;

public class GpuControlFragment extends PreferenceFragment {

    ListPreference maxGpuFrequencyPreference;
    ListPreference minGpuFrequencyPreference;
    ListPreference availableGpuGovernorPreference;

    String[] availableGpuFrequencies;
    String[] availableGpuGovernors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.gpu_preferences);

        maxGpuFrequencyPreference = (ListPreference) findPreference("gpu_max_freq_pref");
        minGpuFrequencyPreference = (ListPreference) findPreference("gpu_min_freq_pref");
        availableGpuGovernorPreference = (ListPreference) findPreference("gpu_governor_pref");

        if (GpuUtils.isGpuFrequencyScalingSupported()) {
            availableGpuFrequencies = GpuUtils.getAvailableGpuFrequencies();
            availableGpuGovernors = GpuUtils.getAvailableGpuGovernors();
            if (availableGpuFrequencies != null && availableGpuFrequencies.length != 0) {
                maxGpuFrequencyPreference.setEntryValues(availableGpuFrequencies);
                maxGpuFrequencyPreference.setEntries(CpuFrequencyUtils.toMhz(availableGpuFrequencies));

                minGpuFrequencyPreference.setEntryValues(availableGpuFrequencies);
                minGpuFrequencyPreference.setEntries(CpuFrequencyUtils.toMhz(availableGpuFrequencies));
            }
            if (availableGpuGovernors != null && availableGpuFrequencies.length != 0) {
                availableGpuGovernorPreference.setEntries(availableGpuGovernors);
                availableGpuGovernorPreference.setEntryValues(availableGpuGovernors);
            }
        }

    }

}