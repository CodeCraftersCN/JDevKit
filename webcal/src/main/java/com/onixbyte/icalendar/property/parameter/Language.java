/*
 * Copyright (C) 2024-2024 OnixByte.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onixbyte.icalendar.property.parameter;

import com.onixbyte.icalendar.property.CalendarResolvable;

/**
 * Language
 *
 * @author Zihlu WANG
 */
public enum Language implements PropertyParameter {

    AFRIKAANS("af"),
    SOUTH_AFRICA_AFRIKAANS("af-ZA"),
    ARABIC("ar"),
    UAE_ARABIC("ar-AE"),
    BAHRAIN_ARABIC("ar-BH"),
    ALGERIA_ARABIC("ar-DZ"),
    EGYPT_ARABIC("ar-EG"),
    IRAQ_ARABIC("ar-IQ"),
    JORDAN_ARABIC("ar-JO"),
    KUWAIT_ARABIC("ar-KW"),
    LEBANON_ARABIC("ar-LB"),
    LIBYA_ARABIC("ar-LY"),
    MOROCCO_ARABIC("ar-MA"),
    OMAN_ARABIC("ar-OM"),
    QATAR_ARABIC("ar-QA"),
    SAUDI_ARABIA_ARABIC_("ar-SA"),
    SYRIA_ARABIC("ar-SY"),
    TUNISIA_ARABIC("ar-TN"),
    YEMEN_ARABIC("ar-YE"),
    LATIN_AZERI("az"),
    AZERBAIJAN_LATIN_AZERI("az-AZ"),
    AZERBAIJAN_CYRILLIC_AZERI("az-Cyrl-AZ"),
    BELARUSIAN("be"),
    BELARUS_BELARUSIAN("be-BY"),
    BULGARIAN("bg"),
    BULGARIA_BULGARIAN("bg-BG"),
    BOSNIA_AND_HERZEGOVINA_BOSNIAN("bs-BA"),
    CATALAN("ca"),
    SPAIN_CATALAN("ca-ES"),
    CZECH("cs"),
    CZECH_REPUBLIC_CZECH("cs-CZ"),
    WELSH("cy"),
    UNITED_KINGDOM_WELSH("cy-GB"),
    DANISH("da"),
    DENMARK_DANISH("da-DK"),
    GERMAN("de"),
    AUSTRIA_GERMAN("de-AT"),
    SWITZERLAND_GERMAN("de-CH"),
    GERMANY_GERMAN("de-DE"),
    LIECHTENSTEIN_GERMAN("de-LI"),
    LUXEMBOURG_GERMAN("de-LU"),
    DIVEHI("dv"),
    MALDIVES_DIVEHI("dv-MV"),
    GREEK("el"),
    GREECE_GREEK("el-GR"),
    ENGLISH("en"),
    AUSTRALIA_ENGLISH("en-AU"),
    BELIZE_ENGLISH("en-BZ"),
    CANADA_ENGLISH("en-CA"),
    CARIBBEAN_ENGLISH("en-CB"),
    UNITED_KINGDOM_ENGLISH("en-GB"),
    IRELAND_ENGLISH("en-IE"),
    JAMAICA_ENGLISH("en-JM"),
    NEW_ZEALAND_ENGLISH("en-NZ"),
    PHILIPPINES_ENGLISH("en-PH"),
    TRINIDAD_AND_TOBAGO_ENGLISH("en-TT"),
    UNITED_STATES_ENGLISH("en-US"),
    SOUTH_AFRICA_ENGLISH("en-ZA"),
    ZIMBABWE_ENGLISH("en-ZW"),
    ESPERANTO("eo"),
    SPANISH("es"),
    ARGENTINA_SPANISH("es-AR"),
    BOLIVIA_SPANISH("es-BO"),
    CHILE_SPANISH("es-CL"),
    COLOMBIA_SPANISH("es-CO"),
    COSTA_RICA_SPANISH("es-CR"),
    DOMINICAN_REPUBLIC_SPANISH("es-DO"),
    ECUADOR_SPANISH("es-EC"),
    SPAIN_SPANISH("es-ES"),
    GUATEMALA_SPANISH("es-GT"),
    HONDURAS_SPANISH("es-HN"),
    MEXICO_SPANISH("es-MX"),
    NICARAGUA_SPANISH("es-NI"),
    PANAMA_SPANISH("es-PA"),
    PERU_SPANISH("es-PE"),
    PUERTO_RICO_SPANISH("es-PR"),
    PARAGUAY_SPANISH("es-PY"),
    EL_SALVADOR_SPANISH("es-SV"),
    URUGUAY_SPANISH("es-UY"),
    VENEZUELA_SPANISH("es-VE"),
    ESTONIAN("et"),
    ESTONIA_ESTONIAN("et-EE"),
    BASQUE("eu"),
    SPAIN_BASQUE("eu-ES"),
    FARSI("fa"),
    IRAN_FARSI("fa-IR"),
    FINNISH("fi"),
    FINLAND_FINNISH("fi-FI"),
    FAROESE("fo"),
    FAROE_ISLANDS_FAROESE("fo-FO"),
    FRENCH("fr"),
    BELGIUM_FRENCH("fr-BE"),
    CANADA_FRENCH("fr-CA"),
    SWITZERLAND_FRENCH("fr-CH"),
    FRANCE_FRENCH("fr-FR"),
    LUXEMBOURG_FRENCH("fr-LU"),
    MONACO_FRENCH("fr-MC"),
    GALICIAN("gl"),
    SPAIN_GALICIAN("gl-ES"),
    GUJARATI("gu"),
    INDIA_GUJARATI("gu-IN"),
    HEBREW("he"),
    ISRAEL_HEBREW("he-IL"),
    HINDI("hi"),
    INDIA_HINDI("hi-IN"),
    CROATIAN("hr"),
    BOSNIA_AND_HERZEGOVINA_CROATIAN("hr-BA"),
    CROATIA_CROATIAN("hr-HR"),
    HUNGARIAN("hu"),
    HUNGARY_HUNGARIAN("hu-HU"),
    ARMENIAN("hy"),
    ARMENIA_ARMENIAN("hy-AM"),
    INDONESIAN("id"),
    INDONESIA_INDONESIAN("id-ID"),
    ICELANDIC("is"),
    ICELAND_ICELANDIC("is-IS"),
    ITALIAN("it"),
    SWITZERLAND_ITALIAN("it-CH"),
    ITALY_ITALIAN("it-IT"),
    JAPANESE("ja"),
    JAPAN_JAPANESE("ja-JA"),
    GEORGIAN("ka"),
    GEORGIA_GEORGIAN("ka-GE"),
    KAZAKH("kk"),
    KAZAKHSTAN_KAZAKH("kk-KZ"),
    KANNADA("kn"),
    INDIA_KANNADA("kn-IN"),
    KOREAN("ko"),
    KOREA_KOREAN("ko-KR"),
    KONKANI("kok"),
    INDIA_KONKANI("kok-IN"),
    KYRGYZ("ky"),
    KYRGYZSTAN_KYRGYZ("ky-KG"),
    LITHUANIAN("lt"),
    LITHUANIA_LITHUANIAN("lt-LT"),
    LATVIAN("lv"),
    LATVIA_LATVIAN("lv-LV"),
    MAORI("mi"),
    NEW_ZEALAND_MAORI("mi-NZ"),
    FYRO_MACEDONIAN("mk"),
    MACEDONIA_FYRO_MACEDONIAN("mk-MK"),
    MONGOLIAN("mn"),
    MONGOLIA_MONGOLIAN("mn-MN"),
    MARATHI("mr"),
    INDIA_MARATHI("mr-IN"),
    MALAY("ms"),
    BRUNEI_DARUSSALAM_MALAY("ms-BN"),
    MALAYSIA_MALAY("ms-MY"),
    MALTESE("mt"),
    MALTA_MALTESE("mt-MT"),
    NORWEGIAN("nb"),
    NORWAY_NORWEGIAN("nb-NO"),
    DUTCH("nl"),
    BELGIUM_DUTCH("nl-BE"),
    NETHERLANDS_DUTCH("nl-NL"),
    NORWAY_NYNORSK_NORWEGIAN("nn-NO"),
    NORTHERN_SOTHO("ns"),
    SOUTH_AFRICA_NORTHERN_SOTHO("ns-ZA"),
    PUNJABI("pa"),
    INDIA_PUNJABI("pa-IN"),
    POLISH("pl"),
    POLAND_POLISH("pl-PL"),
    PASHTO("pt"),
    AFGHANISTAN_PASHTO("ps-AR"),
    PORTUGUESE("pt"),
    BRAZIL_PORTUGUESE("pt-BR"),
    PORTUGAL_PORTUGUESE("pt-PT"),
    QUECHUA("qu"),
    BOLIVIA_QUECHUA("qu-BO"),
    ECUADOR_QUECHUA("qu-EC"),
    PERU_QUECHUA("qu-PE"),
    ROMANIAN("ro"),
    ROMANIA_ROMANIAN("ro-RO"),
    RUSSIAN("ru"),
    RUSSIA_RUSSIAN("ru-RU"),
    SANSKRIT("sa"),
    INDIA_SANSKRIT("sa-IN"),
    SAMI("se"),
    FINLAND_SAMI("se-FI"),
    NORWAY_SAMI("se-NO"),
    SWEDEN_SAMI("se-SE"),
    SLOVAK("sk"),
    SLOVAKIA_SLOVAK("sk-SK"),
    SLOVENIAN("sl"),
    SLOVENIA_SLOVENIAN("sl-SI"),
    ALBANIAN("sq"),
    ALBANIA_ALBANIAN("sq-AL"),
    BOSNIA_AND_HERZEGOVINA_LATIN_SERBIAN("sr-BA"),
    BOSNIA_AND_HERZEGOVINA_CYRILLIC_SERBIAN("sr-Cyrl-BA"),
    SERBIA_AND_MONTENEGRO_LATIN_SERBIAN("sr-SP"),
    SERBIA_AND_MONTENEGRO_CYRILLIC_SERBIAN("sr-Cyrl-SP"),
    SWEDISH("sv"),
    FINLAND_SWEDISH("sv-FI"),
    SWEDEN_SWEDISH("sv-SE"),
    SWAHILI("sw"),
    KENYA_SWAHILI("sw-KE"),
    SYRIAC("syr"),
    SYRIA_SYRIAC("syr-SY"),
    TAMIL("ta"),
    INDIA_TAMIL("ta-IN"),
    TELUGU("te"),
    INDIA_TELUGU("te-IN"),
    THAI("th"),
    THAILAND_THAI("th-TH"),
    TAGALOG("tl"),
    PHILIPPINES_TAGALOG("tl-PH"),
    TSWANA("tn"),
    SOUTH_AFRICA_TSWANA("tn-ZA"),
    TURKISH("tr"),
    TURKEY_TURKISH("tr-TR"),
    TATAR("tt"),
    RUSSIA_TATAR("tt-RU"),
    TSONGA("ts"),
    UKRAINIAN("uk"),
    UKRAINE_UKRAINIAN("uk-UA"),
    URDU("ur"),
    PAKISTAN_URDU("ur-PK"),
    UZBEK("uz"),
    UZBEKISTAN_UZBEK("uz-UZ"),
    UZBEKISTAN_CYRILLIC_UZBEK("uz-Cyrl-UZ"),
    VIETNAMESE("vi"),
    VIETNAM_VIETNAMESE("vi-VN"),
    XHOSA("xh"),
    SOUTH_AFRICA_XHOSA("xh-ZA"),
    CHINESE("zh"),
    SIMPLIFIED_CHINESE("zh-CN"),
    HONG_KONG_CHINESE("zh-HK"),
    MACAU_CHINESE("zh-MO"),
    SINGAPORE_CHINESE("zh-SG"),
    TRADITIONAL_CHINESE("zh-TW"),
    ZULU("zu"),
    SOUTH_AFRICA_ZULU("zu-ZA")
    ;

    private static final String PROPERTY_NAME = "LANGUAGE";

    private final String value;

    Language(String value) {
        this.value = value;
    }

    @Override
    public String resolve() {
        return PROPERTY_NAME + "=" + value;
    }
}
