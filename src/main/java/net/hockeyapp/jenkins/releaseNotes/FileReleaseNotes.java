package net.hockeyapp.jenkins.releaseNotes;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import jenkins.model.Jenkins;
import net.hockeyapp.jenkins.RadioButtonSupport;
import net.hockeyapp.jenkins.RadioButtonSupportDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.export.Exported;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by ungerts on 03.04.14.
 */
public class FileReleaseNotes extends RadioButtonSupport {

    @Exported
    private String fileName;

    @Exported
    private boolean isMarkdown;

    @DataBoundConstructor
    public FileReleaseNotes(String fileName, boolean isMarkdown) {
        this.fileName = fileName;
        this.isMarkdown = isMarkdown;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isMarkdown() {
        return isMarkdown;
    }

    public boolean getIsMarkdown() {
        return isMarkdown;
    }

    public Descriptor<RadioButtonSupport> getDescriptor() {
        return Jenkins.getInstance() == null ? null : Jenkins.getInstance().getDescriptorOrDie(this.getClass());
    }

    @Extension
    public static class DescriptorImpl extends RadioButtonSupportDescriptor<FileReleaseNotes> {

        public DescriptorImpl() {
            super();
            load();
        }

        @Override
        public String getDisplayName() {
            return "Load Release Notes from File";
        }

        @SuppressWarnings("unused")
        public FormValidation doCheckFileName(@QueryParameter String value) throws IOException, ServletException {
            if(value.isEmpty()) {
                return FormValidation.error("You must enter a file name!");
            } else {
                return FormValidation.ok();
            }

        }
    }

}