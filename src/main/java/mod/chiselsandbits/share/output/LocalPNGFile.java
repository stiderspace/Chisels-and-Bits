package mod.chiselsandbits.share.output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import mod.chiselsandbits.blueprints.BlueprintData;
import mod.chiselsandbits.helpers.LocalStrings;
import mod.chiselsandbits.share.ScreenShotEncoder;

public class LocalPNGFile implements IShareOutput
{
	File outFile;

	public LocalPNGFile(
			final File file )
	{
		outFile = file;
	}

	@Override
	public String handleOutput(
			final byte[] compressedData,
			final BufferedImage screenshot ) throws UnsupportedEncodingException, IOException
	{
		ScreenShotEncoder.encodeScreenshot( screenshot, compressedData, new FileOutputStream( outFile ) );
		return LocalStrings.ShareFile.toString();
	}

	@Override
	public BlueprintData getData()
	{
		BlueprintData bpd = new BlueprintData( null );

		try
		{
			bpd.setLocalSource( outFile.getAbsolutePath() );
			bpd.loadData( new FileInputStream( outFile ) );
		}
		catch ( IOException e )
		{

		}

		return bpd;
	}

}
