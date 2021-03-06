package com.github.kostyasha.yad.commons;

import com.github.kostyasha.yad_docker_java.com.github.dockerjava.api.DockerClient;
import com.github.kostyasha.yad_docker_java.com.github.dockerjava.core.command.StopContainerCmdImpl;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import java.io.Serializable;

/**
 * @author Kanstantsin Shautsou
 * @see StopContainerCmdImpl
 */
public class DockerStopContainer extends AbstractDescribableImpl<DockerStopContainer> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int timeout = 10;

    @DataBoundConstructor
    public DockerStopContainer() {
    }

    public int getTimeout() {
        return timeout;
    }

    @DataBoundSetter
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void exec(DockerClient client, String containerId) {
        client.stopContainerCmd(containerId)
                .withTimeout(timeout)
                .exec();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DockerStopContainer that = (DockerStopContainer) o;

        return new EqualsBuilder()
                .append(timeout, that.timeout)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(timeout)
                .toHashCode();
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<DockerStopContainer> {
        @Override
        public String getDisplayName() {
            return "Docker Stop Container";
        }
    }
}
