package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
final class MetadataUtil {
    private static final String LANGUAGE_UNDEFINED = "und";
    private static final String MDTA_KEY_ANDROID_CAPTURE_FPS = "com.android.capture.fps";
    private static final int MDTA_TYPE_INDICATOR_FLOAT = 23;
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final String TAG = "MetadataUtil";
    private static final int TYPE_TOP_BYTE_COPYRIGHT = 169;
    private static final int TYPE_TOP_BYTE_REPLACEMENT = 253;
    private static final int SHORT_TYPE_NAME_1 = Util.getIntegerCodeForString("nam");
    private static final int SHORT_TYPE_NAME_2 = Util.getIntegerCodeForString("trk");
    private static final int SHORT_TYPE_COMMENT = Util.getIntegerCodeForString("cmt");
    private static final int SHORT_TYPE_YEAR = Util.getIntegerCodeForString("day");
    private static final int SHORT_TYPE_ARTIST = Util.getIntegerCodeForString("ART");
    private static final int SHORT_TYPE_ENCODER = Util.getIntegerCodeForString("too");
    private static final int SHORT_TYPE_ALBUM = Util.getIntegerCodeForString("alb");
    private static final int SHORT_TYPE_COMPOSER_1 = Util.getIntegerCodeForString("com");
    private static final int SHORT_TYPE_COMPOSER_2 = Util.getIntegerCodeForString("wrt");
    private static final int SHORT_TYPE_LYRICS = Util.getIntegerCodeForString("lyr");
    private static final int SHORT_TYPE_GENRE = Util.getIntegerCodeForString("gen");
    private static final int TYPE_COVER_ART = Util.getIntegerCodeForString("covr");
    private static final int TYPE_GENRE = Util.getIntegerCodeForString("gnre");
    private static final int TYPE_GROUPING = Util.getIntegerCodeForString("grp");
    private static final int TYPE_DISK_NUMBER = Util.getIntegerCodeForString("disk");
    private static final int TYPE_TRACK_NUMBER = Util.getIntegerCodeForString("trkn");
    private static final int TYPE_TEMPO = Util.getIntegerCodeForString("tmpo");
    private static final int TYPE_COMPILATION = Util.getIntegerCodeForString("cpil");
    private static final int TYPE_ALBUM_ARTIST = Util.getIntegerCodeForString("aART");
    private static final int TYPE_SORT_TRACK_NAME = Util.getIntegerCodeForString("sonm");
    private static final int TYPE_SORT_ALBUM = Util.getIntegerCodeForString("soal");
    private static final int TYPE_SORT_ARTIST = Util.getIntegerCodeForString("soar");
    private static final int TYPE_SORT_ALBUM_ARTIST = Util.getIntegerCodeForString("soaa");
    private static final int TYPE_SORT_COMPOSER = Util.getIntegerCodeForString("soco");
    private static final int TYPE_RATING = Util.getIntegerCodeForString("rtng");
    private static final int TYPE_GAPLESS_ALBUM = Util.getIntegerCodeForString("pgap");
    private static final int TYPE_TV_SORT_SHOW = Util.getIntegerCodeForString("sosn");
    private static final int TYPE_TV_SHOW = Util.getIntegerCodeForString("tvsh");
    private static final int TYPE_INTERNAL = Util.getIntegerCodeForString(InternalFrame.ID);
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    private MetadataUtil() {
    }

    public static Format getFormatWithMetadata(int i, Format format, Metadata metadata, Metadata metadata2, GaplessInfoHolder gaplessInfoHolder) {
        if (i == 1) {
            if (gaplessInfoHolder.hasGaplessInfo()) {
                format = format.copyWithGaplessInfo(gaplessInfoHolder.encoderDelay, gaplessInfoHolder.encoderPadding);
            }
            return metadata != null ? format.copyWithMetadata(metadata) : format;
        }
        if (i != 2 || metadata2 == null) {
            return format;
        }
        for (int i2 = 0; i2 < metadata2.length(); i2++) {
            Metadata.Entry entry = metadata2.get(i2);
            if (entry instanceof MdtaMetadataEntry) {
                MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                if (MDTA_KEY_ANDROID_CAPTURE_FPS.equals(mdtaMetadataEntry.key) && mdtaMetadataEntry.typeIndicator == 23) {
                    try {
                        format = format.copyWithFrameRate(ByteBuffer.wrap(mdtaMetadataEntry.value).asFloatBuffer().get()).copyWithMetadata(new Metadata(mdtaMetadataEntry));
                    } catch (NumberFormatException unused) {
                        Log.w(TAG, "Ignoring invalid framerate");
                    }
                }
            }
        }
        return format;
    }

    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
        int i = parsableByteArray.readInt();
        int i2 = (i >> 24) & 255;
        try {
            if (i2 == TYPE_TOP_BYTE_COPYRIGHT || i2 == TYPE_TOP_BYTE_REPLACEMENT) {
                int i3 = 16777215 & i;
                if (i3 == SHORT_TYPE_COMMENT) {
                    CommentFrame commentAttribute = parseCommentAttribute(i, parsableByteArray);
                    parsableByteArray.setPosition(position);
                    return commentAttribute;
                }
                if (i3 != SHORT_TYPE_NAME_1 && i3 != SHORT_TYPE_NAME_2) {
                    if (i3 != SHORT_TYPE_COMPOSER_1 && i3 != SHORT_TYPE_COMPOSER_2) {
                        if (i3 == SHORT_TYPE_YEAR) {
                            TextInformationFrame textAttribute = parseTextAttribute(i, "TDRC", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute;
                        }
                        if (i3 == SHORT_TYPE_ARTIST) {
                            TextInformationFrame textAttribute2 = parseTextAttribute(i, "TPE1", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute2;
                        }
                        if (i3 == SHORT_TYPE_ENCODER) {
                            TextInformationFrame textAttribute3 = parseTextAttribute(i, "TSSE", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute3;
                        }
                        if (i3 == SHORT_TYPE_ALBUM) {
                            TextInformationFrame textAttribute4 = parseTextAttribute(i, "TALB", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute4;
                        }
                        if (i3 == SHORT_TYPE_LYRICS) {
                            TextInformationFrame textAttribute5 = parseTextAttribute(i, "USLT", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute5;
                        }
                        if (i3 == SHORT_TYPE_GENRE) {
                            TextInformationFrame textAttribute6 = parseTextAttribute(i, "TCON", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute6;
                        }
                        if (i3 == TYPE_GROUPING) {
                            TextInformationFrame textAttribute7 = parseTextAttribute(i, "TIT1", parsableByteArray);
                            parsableByteArray.setPosition(position);
                            return textAttribute7;
                        }
                    }
                    TextInformationFrame textAttribute8 = parseTextAttribute(i, "TCOM", parsableByteArray);
                    parsableByteArray.setPosition(position);
                    return textAttribute8;
                }
                TextInformationFrame textAttribute9 = parseTextAttribute(i, "TIT2", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute9;
            }
            if (i == TYPE_GENRE) {
                TextInformationFrame standardGenreAttribute = parseStandardGenreAttribute(parsableByteArray);
                parsableByteArray.setPosition(position);
                return standardGenreAttribute;
            }
            if (i == TYPE_DISK_NUMBER) {
                TextInformationFrame indexAndCountAttribute = parseIndexAndCountAttribute(i, "TPOS", parsableByteArray);
                parsableByteArray.setPosition(position);
                return indexAndCountAttribute;
            }
            if (i == TYPE_TRACK_NUMBER) {
                TextInformationFrame indexAndCountAttribute2 = parseIndexAndCountAttribute(i, "TRCK", parsableByteArray);
                parsableByteArray.setPosition(position);
                return indexAndCountAttribute2;
            }
            if (i == TYPE_TEMPO) {
                Id3Frame uint8Attribute = parseUint8Attribute(i, "TBPM", parsableByteArray, true, false);
                parsableByteArray.setPosition(position);
                return uint8Attribute;
            }
            if (i == TYPE_COMPILATION) {
                Id3Frame uint8Attribute2 = parseUint8Attribute(i, "TCMP", parsableByteArray, true, true);
                parsableByteArray.setPosition(position);
                return uint8Attribute2;
            }
            if (i == TYPE_COVER_ART) {
                ApicFrame coverArt = parseCoverArt(parsableByteArray);
                parsableByteArray.setPosition(position);
                return coverArt;
            }
            if (i == TYPE_ALBUM_ARTIST) {
                TextInformationFrame textAttribute10 = parseTextAttribute(i, "TPE2", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute10;
            }
            if (i == TYPE_SORT_TRACK_NAME) {
                TextInformationFrame textAttribute11 = parseTextAttribute(i, "TSOT", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute11;
            }
            if (i == TYPE_SORT_ALBUM) {
                TextInformationFrame textAttribute12 = parseTextAttribute(i, "TSO2", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute12;
            }
            if (i == TYPE_SORT_ARTIST) {
                TextInformationFrame textAttribute13 = parseTextAttribute(i, "TSOA", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute13;
            }
            if (i == TYPE_SORT_ALBUM_ARTIST) {
                TextInformationFrame textAttribute14 = parseTextAttribute(i, "TSOP", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute14;
            }
            if (i == TYPE_SORT_COMPOSER) {
                TextInformationFrame textAttribute15 = parseTextAttribute(i, "TSOC", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute15;
            }
            if (i == TYPE_RATING) {
                Id3Frame uint8Attribute3 = parseUint8Attribute(i, "ITUNESADVISORY", parsableByteArray, false, false);
                parsableByteArray.setPosition(position);
                return uint8Attribute3;
            }
            if (i == TYPE_GAPLESS_ALBUM) {
                Id3Frame uint8Attribute4 = parseUint8Attribute(i, "ITUNESGAPLESS", parsableByteArray, false, true);
                parsableByteArray.setPosition(position);
                return uint8Attribute4;
            }
            if (i == TYPE_TV_SORT_SHOW) {
                TextInformationFrame textAttribute16 = parseTextAttribute(i, "TVSHOWSORT", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute16;
            }
            if (i == TYPE_TV_SHOW) {
                TextInformationFrame textAttribute17 = parseTextAttribute(i, "TVSHOW", parsableByteArray);
                parsableByteArray.setPosition(position);
                return textAttribute17;
            }
            if (i == TYPE_INTERNAL) {
                Id3Frame internalAttribute = parseInternalAttribute(parsableByteArray, position);
                parsableByteArray.setPosition(position);
                return internalAttribute;
            }
            Log.d(TAG, "Skipped unknown metadata entry: " + Atom.getAtomTypeString(i));
            parsableByteArray.setPosition(position);
            return null;
        } catch (Throwable th) {
            parsableByteArray.setPosition(position);
            throw th;
        }
    }

    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray parsableByteArray, int i, String str) {
        while (true) {
            int position = parsableByteArray.getPosition();
            if (position >= i) {
                return null;
            }
            int i2 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_data) {
                int i3 = parsableByteArray.readInt();
                int i4 = parsableByteArray.readInt();
                int i5 = i2 - 16;
                byte[] bArr = new byte[i5];
                parsableByteArray.readBytes(bArr, 0, i5);
                return new MdtaMetadataEntry(str, bArr, i4, i3);
            }
            parsableByteArray.setPosition(position + i2);
        }
    }

    private static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, null, parsableByteArray.readNullTerminatedString(i2 - 16));
        }
        Log.w(TAG, "Failed to parse text attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    private static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            String nullTerminatedString = parsableByteArray.readNullTerminatedString(i2 - 16);
            return new CommentFrame("und", nullTerminatedString, nullTerminatedString);
        }
        Log.w(TAG, "Failed to parse comment attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    private static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int uint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            uint8AttributeValue = Math.min(1, uint8AttributeValue);
        }
        if (uint8AttributeValue >= 0) {
            if (z) {
                return new TextInformationFrame(str, null, Integer.toString(uint8AttributeValue));
            }
            return new CommentFrame("und", str, Integer.toString(uint8AttributeValue));
        }
        Log.w(TAG, "Failed to parse uint8 attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data && i2 >= 22) {
            parsableByteArray.skipBytes(10);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if (unsignedShort > 0) {
                String str2 = "" + unsignedShort;
                int unsignedShort2 = parsableByteArray.readUnsignedShort();
                if (unsignedShort2 > 0) {
                    str2 = str2 + "/" + unsignedShort2;
                }
                return new TextInformationFrame(str, null, str2);
            }
        }
        Log.w(TAG, "Failed to parse index/count attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0011  */
    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray parsableByteArray) {
        String str;
        int uint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (uint8AttributeValue > 0) {
            String[] strArr = STANDARD_GENRES;
            if (uint8AttributeValue <= strArr.length) {
                str = strArr[uint8AttributeValue - 1];
            } else {
                str = null;
            }
        } else {
            str = null;
        }
        if (str != null) {
            return new TextInformationFrame("TCON", null, str);
        }
        Log.w(TAG, "Failed to parse standard genre code");
        return null;
    }

    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        String str;
        int i = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            if (fullAtomFlags == 13) {
                str = "image/jpeg";
            } else {
                str = fullAtomFlags == 14 ? "image/png" : null;
            }
            if (str == null) {
                Log.w(TAG, "Unrecognized cover art flags: " + fullAtomFlags);
                return null;
            }
            parsableByteArray.skipBytes(4);
            int i2 = i - 16;
            byte[] bArr = new byte[i2];
            parsableByteArray.readBytes(bArr, 0, i2);
            return new ApicFrame(str, null, 3, bArr);
        }
        Log.w(TAG, "Failed to parse cover art attribute");
        return null;
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        int i2 = -1;
        int i3 = -1;
        String nullTerminatedString = null;
        String nullTerminatedString2 = null;
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int i4 = parsableByteArray.readInt();
            int i5 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (i5 == Atom.TYPE_mean) {
                nullTerminatedString = parsableByteArray.readNullTerminatedString(i4 - 12);
            } else if (i5 == Atom.TYPE_name) {
                nullTerminatedString2 = parsableByteArray.readNullTerminatedString(i4 - 12);
            } else {
                if (i5 == Atom.TYPE_data) {
                    i2 = position;
                    i3 = i4;
                }
                parsableByteArray.skipBytes(i4 - 12);
            }
        }
        if (nullTerminatedString == null || nullTerminatedString2 == null || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(nullTerminatedString, nullTerminatedString2, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w(TAG, "Failed to parse uint8 attribute value");
        return -1;
    }
}
