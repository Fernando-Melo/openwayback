/* CDXLineToSearchResultAdaptor
 *
 * $Id$
 *
 * Created on 2:27:16 PM Aug 17, 2006.
 *
 * Copyright (C) 2006 Internet Archive.
 *
 * This file is part of Wayback.
 *
 * Wayback is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * any later version.
 *
 * Wayback is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with Wayback; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.archive.wayback.resourceindex.cdx;


import org.archive.wayback.core.CaptureSearchResult;
import org.archive.wayback.util.Adapter;

/**
 * Adapter that converts a CDX record String into a CaptureSearchResult
 *
 * @author brad
 * @version $Date$, $Revision$
 */
public class CDXLineToSearchResultAdapter implements Adapter<String,CaptureSearchResult> {

	public CaptureSearchResult adapt(String line) {
		return doAdapt(line);
	}
	/**
	 * @param line
	 * @return SearchResult representation of input line
	 */
	public static CaptureSearchResult doAdapt(String line) {
		CaptureSearchResult result = new CaptureSearchResult();
		String[] tokens = line.split(" ");
		if (tokens.length != 9) {
			return null;
			//throw new IllegalArgumentException("Need 9 columns("+line+")");
		}
		String urlKey = tokens[0];
		String captureTS = tokens[1];
		String originalUrl = tokens[2];
		String mimeType = tokens[3];
		String httpCode = tokens[4];
		String digest = tokens[5];
		String redirectUrl = tokens[6];
		long compressedOffset = -1;
		if(!tokens[7].equals("-")) {
			compressedOffset = Long.parseLong(tokens[7]);
		}
		String fileName = tokens[8];
		result.setUrlKey(urlKey);
		result.setCaptureTimestamp(captureTS);
		result.setOriginalUrl(originalUrl);
		result.setMimeType(mimeType);
		result.setHttpCode(httpCode);
		result.setDigest(digest);
		result.setRedirectUrl(redirectUrl);
		result.setOffset(compressedOffset);
		result.setFile(fileName);

		return result;
	}
}
