package com.bosgii.internshipmanagement.requests;

import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.enums.VersionStatus;

public class ChangeVersionRequest {
    VersionStatus versionStatus;

    public VersionStatus getVersionStatus() {
        return versionStatus;
    }

    public void setVersionStatus(VersionStatus versionStatus) {
        this.versionStatus = versionStatus;
    }
}
