package org.apache.easyant.tasks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

/**
 * Alternative Import module implementation. This implementation is designed for test purpose. It doesn't take in
 * consideration : skipping module or buildConfiguration features.
 * Example :
 *  <pre>
 * &lt;import-test-module moduleIvy="/path/to/module.ivy" /&gt;
 * </pre>
 * Same example specifying sourceDirectory 
 * <pre>
 * &lt;import-test-module moduleIvy="/path/to/module.ivy" sourceDirectory="/path/to/sourcedirectory"/&gt;
 * </pre>
 */
public class ImportTestModule extends AbstractImport {

    private File moduleIvy;
    private File sourceDirectory;

    public void execute() throws BuildException {
        if (moduleIvy == null || !moduleIvy.exists()) {
            throw new BuildException("moduleIvy is not specified, or the requested file doesn't exists");
        }
        if (sourceDirectory == null) {
            throw new BuildException("sourceDirectory is not specified");
        }
        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            throw new BuildException("sourceDirectory does not exists or is not a directory");
        }

        try {
             
            ResolveReport report = getEasyAntIvyInstance().getResolveEngine().resolve(moduleIvy);
            //tiny hack report.getModuleDescriptor.getModuleRevisionId() return a caller instance
            ModuleRevisionId moduleRevisionId =report.getModuleDescriptor().getAllArtifacts()[0].getModuleRevisionId();
            importModule(moduleRevisionId,report);

        } catch (ParseException e) {
            throw new BuildException("Can't parse module descriptor", e);
        } catch (IOException e) {
            throw new BuildException("Can't parse module descriptor", e);
        }

    }

    @Override
    protected void importModule(ModuleRevisionId moduleRevisionId,ResolveReport report) {
        // Check dependency on core
        checkCoreCompliance(report, getProvidedConf());

        Path path = createModulePath(moduleRevisionId);
        File antFile = null;
        // handle downloaded resources
        for (int j = 0; j < report.getConfigurationReport(getMainConf()).getAllArtifactsReports().length; j++) {
            ArtifactDownloadReport artifact = report.getConfigurationReport(getMainConf()).getAllArtifactsReports()[j];

            if ("jar".equals(artifact.getType())) {
                path.createPathElement().setLocation(artifact.getLocalFile());
            }
        }
        // handle local resources (ant file, and all additional files such as properties)
        for (int i = 0; i < report.getModuleDescriptor().getAllArtifacts().length; i++) {
            Artifact artifact = report.getModuleDescriptor().getAllArtifacts()[i];
            File localResourceFile = new File(sourceDirectory, artifact.getName() + "." + artifact.getExt());
            if ("ant".equals(artifact.getType())) {
                antFile = localResourceFile;
            } else {
                handleOtherResourceFile(moduleRevisionId,artifact.getName(),artifact.getExt(), localResourceFile);
            }
        }
        // do effective import
        if (antFile != null && antFile.exists()) {
            doEffectiveImport(antFile);
        }
    }

    public File getModuleIvy() {
        return moduleIvy;
    }

    public void setModuleIvy(File moduleIvy) {
        this.moduleIvy = moduleIvy;
    }

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(File sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

}