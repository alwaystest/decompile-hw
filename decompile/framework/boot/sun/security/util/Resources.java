package sun.security.util;

import java.net.HttpURLConnection;
import java.util.ListResourceBundle;

public class Resources extends ListResourceBundle {
    private static final Object[][] contents;

    static {
        r0 = new Object[378][];
        r0[0] = new Object[]{"SPACE", " "};
        r0[1] = new Object[]{"2SPACE", "  "};
        r0[2] = new Object[]{"6SPACE", "      "};
        r0[3] = new Object[]{"COMMA", ", "};
        r0[4] = new Object[]{"NEWLINE", "\n"};
        r0[5] = new Object[]{"STAR", "*******************************************"};
        r0[6] = new Object[]{"STARNN", "*******************************************\n\n"};
        r0[7] = new Object[]{".OPTION.", " [OPTION]..."};
        r0[8] = new Object[]{"Options.", "Options:"};
        r0[9] = new Object[]{"Use.keytool.help.for.all.available.commands", "Use \"keytool -help\" for all available commands"};
        r0[10] = new Object[]{"Key.and.Certificate.Management.Tool", "Key and Certificate Management Tool"};
        r0[11] = new Object[]{"Commands.", "Commands:"};
        r0[12] = new Object[]{"Use.keytool.command.name.help.for.usage.of.command.name", "Use \"keytool -command_name -help\" for usage of command_name"};
        r0[13] = new Object[]{"Generates.a.certificate.request", "Generates a certificate request"};
        r0[14] = new Object[]{"Changes.an.entry.s.alias", "Changes an entry's alias"};
        r0[15] = new Object[]{"Deletes.an.entry", "Deletes an entry"};
        r0[16] = new Object[]{"Exports.certificate", "Exports certificate"};
        r0[17] = new Object[]{"Generates.a.key.pair", "Generates a key pair"};
        r0[18] = new Object[]{"Generates.a.secret.key", "Generates a secret key"};
        r0[19] = new Object[]{"Generates.certificate.from.a.certificate.request", "Generates certificate from a certificate request"};
        r0[20] = new Object[]{"Generates.CRL", "Generates CRL"};
        r0[21] = new Object[]{"Imports.entries.from.a.JDK.1.1.x.style.identity.database", "Imports entries from a JDK 1.1.x-style identity database"};
        r0[22] = new Object[]{"Imports.a.certificate.or.a.certificate.chain", "Imports a certificate or a certificate chain"};
        r0[23] = new Object[]{"Imports.one.or.all.entries.from.another.keystore", "Imports one or all entries from another keystore"};
        r0[24] = new Object[]{"Clones.a.key.entry", "Clones a key entry"};
        r0[25] = new Object[]{"Changes.the.key.password.of.an.entry", "Changes the key password of an entry"};
        r0[26] = new Object[]{"Lists.entries.in.a.keystore", "Lists entries in a keystore"};
        r0[27] = new Object[]{"Prints.the.content.of.a.certificate", "Prints the content of a certificate"};
        r0[28] = new Object[]{"Prints.the.content.of.a.certificate.request", "Prints the content of a certificate request"};
        r0[29] = new Object[]{"Prints.the.content.of.a.CRL.file", "Prints the content of a CRL file"};
        r0[30] = new Object[]{"Generates.a.self.signed.certificate", "Generates a self-signed certificate"};
        r0[31] = new Object[]{"Changes.the.store.password.of.a.keystore", "Changes the store password of a keystore"};
        r0[32] = new Object[]{"alias.name.of.the.entry.to.process", "alias name of the entry to process"};
        r0[33] = new Object[]{"destination.alias", "destination alias"};
        r0[34] = new Object[]{"destination.key.password", "destination key password"};
        r0[35] = new Object[]{"destination.keystore.name", "destination keystore name"};
        r0[36] = new Object[]{"destination.keystore.password.protected", "destination keystore password protected"};
        r0[37] = new Object[]{"destination.keystore.provider.name", "destination keystore provider name"};
        r0[38] = new Object[]{"destination.keystore.password", "destination keystore password"};
        r0[39] = new Object[]{"destination.keystore.type", "destination keystore type"};
        r0[40] = new Object[]{"distinguished.name", "distinguished name"};
        r0[41] = new Object[]{"X.509.extension", "X.509 extension"};
        r0[42] = new Object[]{"output.file.name", "output file name"};
        r0[43] = new Object[]{"input.file.name", "input file name"};
        r0[44] = new Object[]{"key.algorithm.name", "key algorithm name"};
        r0[45] = new Object[]{"key.password", "key password"};
        r0[46] = new Object[]{"key.bit.size", "key bit size"};
        r0[47] = new Object[]{"keystore.name", "keystore name"};
        r0[48] = new Object[]{"new.password", "new password"};
        r0[49] = new Object[]{"do.not.prompt", "do not prompt"};
        r0[50] = new Object[]{"password.through.protected.mechanism", "password through protected mechanism"};
        r0[51] = new Object[]{"provider.argument", "provider argument"};
        r0[52] = new Object[]{"provider.class.name", "provider class name"};
        r0[53] = new Object[]{"provider.name", "provider name"};
        r0[54] = new Object[]{"provider.classpath", "provider classpath"};
        r0[55] = new Object[]{"output.in.RFC.style", "output in RFC style"};
        r0[56] = new Object[]{"signature.algorithm.name", "signature algorithm name"};
        r0[57] = new Object[]{"source.alias", "source alias"};
        r0[58] = new Object[]{"source.key.password", "source key password"};
        r0[59] = new Object[]{"source.keystore.name", "source keystore name"};
        r0[60] = new Object[]{"source.keystore.password.protected", "source keystore password protected"};
        r0[61] = new Object[]{"source.keystore.provider.name", "source keystore provider name"};
        r0[62] = new Object[]{"source.keystore.password", "source keystore password"};
        r0[63] = new Object[]{"source.keystore.type", "source keystore type"};
        r0[64] = new Object[]{"SSL.server.host.and.port", "SSL server host and port"};
        r0[65] = new Object[]{"signed.jar.file", "signed jar file"};
        r0[66] = new Object[]{"certificate.validity.start.date.time", "certificate validity start date/time"};
        r0[67] = new Object[]{"keystore.password", "keystore password"};
        r0[68] = new Object[]{"keystore.type", "keystore type"};
        r0[69] = new Object[]{"trust.certificates.from.cacerts", "trust certificates from cacerts"};
        r0[70] = new Object[]{"verbose.output", "verbose output"};
        r0[71] = new Object[]{"validity.number.of.days", "validity number of days"};
        r0[72] = new Object[]{"Serial.ID.of.cert.to.revoke", "Serial ID of cert to revoke"};
        r0[73] = new Object[]{"keytool.error.", "keytool error: "};
        r0[74] = new Object[]{"Illegal.option.", "Illegal option:  "};
        r0[75] = new Object[]{"Illegal.value.", "Illegal value: "};
        r0[76] = new Object[]{"Unknown.password.type.", "Unknown password type: "};
        r0[77] = new Object[]{"Cannot.find.environment.variable.", "Cannot find environment variable: "};
        r0[78] = new Object[]{"Cannot.find.file.", "Cannot find file: "};
        r0[79] = new Object[]{"Command.option.flag.needs.an.argument.", "Command option {0} needs an argument."};
        r0[80] = new Object[]{"Warning.Different.store.and.key.passwords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.command.value.", "Warning:  Different store and key passwords not supported for PKCS12 KeyStores. Ignoring user-specified {0} value."};
        r0[81] = new Object[]{".keystore.must.be.NONE.if.storetype.is.{0}", "-keystore must be NONE if -storetype is {0}"};
        r0[82] = new Object[]{"Too.many.retries.program.terminated", "Too many retries, program terminated"};
        r0[83] = new Object[]{".storepasswd.and.keypasswd.commands.not.supported.if.storetype.is.{0}", "-storepasswd and -keypasswd commands not supported if -storetype is {0}"};
        r0[84] = new Object[]{".keypasswd.commands.not.supported.if.storetype.is.PKCS12", "-keypasswd commands not supported if -storetype is PKCS12"};
        r0[85] = new Object[]{".keypass.and.new.can.not.be.specified.if.storetype.is.{0}", "-keypass and -new can not be specified if -storetype is {0}"};
        r0[86] = new Object[]{"if.protected.is.specified.then.storepass.keypass.and.new.must.not.be.specified", "if -protected is specified, then -storepass, -keypass, and -new must not be specified"};
        r0[87] = new Object[]{"if.srcprotected.is.specified.then.srcstorepass.and.srckeypass.must.not.be.specified", "if -srcprotected is specified, then -srcstorepass and -srckeypass must not be specified"};
        r0[88] = new Object[]{"if.keystore.is.not.password.protected.then.storepass.keypass.and.new.must.not.be.specified", "if keystore is not password protected, then -storepass, -keypass, and -new must not be specified"};
        r0[89] = new Object[]{"if.source.keystore.is.not.password.protected.then.srcstorepass.and.srckeypass.must.not.be.specified", "if source keystore is not password protected, then -srcstorepass and -srckeypass must not be specified"};
        r0[90] = new Object[]{"Illegal.startdate.value", "Illegal startdate value"};
        r0[91] = new Object[]{"Validity.must.be.greater.than.zero", "Validity must be greater than zero"};
        r0[92] = new Object[]{"provName.not.a.provider", "{0} not a provider"};
        r0[93] = new Object[]{"Usage.error.no.command.provided", "Usage error: no command provided"};
        r0[94] = new Object[]{"Source.keystore.file.exists.but.is.empty.", "Source keystore file exists, but is empty: "};
        r0[95] = new Object[]{"Please.specify.srckeystore", "Please specify -srckeystore"};
        r0[96] = new Object[]{"Must.not.specify.both.v.and.rfc.with.list.command", "Must not specify both -v and -rfc with 'list' command"};
        r0[97] = new Object[]{"Key.password.must.be.at.least.6.characters", "Key password must be at least 6 characters"};
        r0[98] = new Object[]{"New.password.must.be.at.least.6.characters", "New password must be at least 6 characters"};
        r0[99] = new Object[]{"Keystore.file.exists.but.is.empty.", "Keystore file exists, but is empty: "};
        r0[100] = new Object[]{"Keystore.file.does.not.exist.", "Keystore file does not exist: "};
        r0[101] = new Object[]{"Must.specify.destination.alias", "Must specify destination alias"};
        r0[102] = new Object[]{"Must.specify.alias", "Must specify alias"};
        r0[103] = new Object[]{"Keystore.password.must.be.at.least.6.characters", "Keystore password must be at least 6 characters"};
        r0[104] = new Object[]{"Enter.keystore.password.", "Enter keystore password:  "};
        r0[105] = new Object[]{"Enter.source.keystore.password.", "Enter source keystore password:  "};
        r0[106] = new Object[]{"Enter.destination.keystore.password.", "Enter destination keystore password:  "};
        r0[107] = new Object[]{"Keystore.password.is.too.short.must.be.at.least.6.characters", "Keystore password is too short - must be at least 6 characters"};
        r0[108] = new Object[]{"Unknown.Entry.Type", "Unknown Entry Type"};
        r0[109] = new Object[]{"Too.many.failures.Alias.not.changed", "Too many failures. Alias not changed"};
        r0[110] = new Object[]{"Entry.for.alias.alias.successfully.imported.", "Entry for alias {0} successfully imported."};
        r0[111] = new Object[]{"Entry.for.alias.alias.not.imported.", "Entry for alias {0} not imported."};
        r0[112] = new Object[]{"Problem.importing.entry.for.alias.alias.exception.Entry.for.alias.alias.not.imported.", "Problem importing entry for alias {0}: {1}.\nEntry for alias {0} not imported."};
        r0[113] = new Object[]{"Import.command.completed.ok.entries.successfully.imported.fail.entries.failed.or.cancelled", "Import command completed:  {0} entries successfully imported, {1} entries failed or cancelled"};
        r0[114] = new Object[]{"Warning.Overwriting.existing.alias.alias.in.destination.keystore", "Warning: Overwriting existing alias {0} in destination keystore"};
        r0[115] = new Object[]{"Existing.entry.alias.alias.exists.overwrite.no.", "Existing entry alias {0} exists, overwrite? [no]:  "};
        r0[116] = new Object[]{"Too.many.failures.try.later", "Too many failures - try later"};
        r0[117] = new Object[]{"Certification.request.stored.in.file.filename.", "Certification request stored in file <{0}>"};
        r0[118] = new Object[]{"Submit.this.to.your.CA", "Submit this to your CA"};
        r0[119] = new Object[]{"if.alias.not.specified.destalias.srckeypass.and.destkeypass.must.not.be.specified", "if alias not specified, destalias, srckeypass, and destkeypass must not be specified"};
        r0[120] = new Object[]{"Certificate.stored.in.file.filename.", "Certificate stored in file <{0}>"};
        r0[121] = new Object[]{"Certificate.reply.was.installed.in.keystore", "Certificate reply was installed in keystore"};
        r0[122] = new Object[]{"Certificate.reply.was.not.installed.in.keystore", "Certificate reply was not installed in keystore"};
        r0[123] = new Object[]{"Certificate.was.added.to.keystore", "Certificate was added to keystore"};
        r0[124] = new Object[]{"Certificate.was.not.added.to.keystore", "Certificate was not added to keystore"};
        r0[125] = new Object[]{".Storing.ksfname.", "[Storing {0}]"};
        r0[126] = new Object[]{"alias.has.no.public.key.certificate.", "{0} has no public key (certificate)"};
        r0[127] = new Object[]{"Cannot.derive.signature.algorithm", "Cannot derive signature algorithm"};
        r0[128] = new Object[]{"Alias.alias.does.not.exist", "Alias <{0}> does not exist"};
        r0[129] = new Object[]{"Alias.alias.has.no.certificate", "Alias <{0}> has no certificate"};
        r0[130] = new Object[]{"Key.pair.not.generated.alias.alias.already.exists", "Key pair not generated, alias <{0}> already exists"};
        r0[131] = new Object[]{"Generating.keysize.bit.keyAlgName.key.pair.and.self.signed.certificate.sigAlgName.with.a.validity.of.validality.days.for", "Generating {0} bit {1} key pair and self-signed certificate ({2}) with a validity of {3} days\n\tfor: {4}"};
        r0[132] = new Object[]{"Enter.key.password.for.alias.", "Enter key password for <{0}>"};
        r0[133] = new Object[]{".RETURN.if.same.as.keystore.password.", "\t(RETURN if same as keystore password):  "};
        r0[134] = new Object[]{"Key.password.is.too.short.must.be.at.least.6.characters", "Key password is too short - must be at least 6 characters"};
        r0[135] = new Object[]{"Too.many.failures.key.not.added.to.keystore", "Too many failures - key not added to keystore"};
        r0[136] = new Object[]{"Destination.alias.dest.already.exists", "Destination alias <{0}> already exists"};
        r0[137] = new Object[]{"Password.is.too.short.must.be.at.least.6.characters", "Password is too short - must be at least 6 characters"};
        r0[138] = new Object[]{"Too.many.failures.Key.entry.not.cloned", "Too many failures. Key entry not cloned"};
        r0[139] = new Object[]{"key.password.for.alias.", "key password for <{0}>"};
        r0[140] = new Object[]{"Keystore.entry.for.id.getName.already.exists", "Keystore entry for <{0}> already exists"};
        r0[141] = new Object[]{"Creating.keystore.entry.for.id.getName.", "Creating keystore entry for <{0}> ..."};
        r0[142] = new Object[]{"No.entries.from.identity.database.added", "No entries from identity database added"};
        r0[143] = new Object[]{"Alias.name.alias", "Alias name: {0}"};
        r0[144] = new Object[]{"Creation.date.keyStore.getCreationDate.alias.", "Creation date: {0,date}"};
        r0[145] = new Object[]{"alias.keyStore.getCreationDate.alias.", "{0}, {1,date}, "};
        r0[146] = new Object[]{"alias.", "{0}, "};
        r0[147] = new Object[]{"Entry.type.type.", "Entry type: {0}"};
        r0[148] = new Object[]{"Certificate.chain.length.", "Certificate chain length: "};
        r0[149] = new Object[]{"Certificate.i.1.", "Certificate[{0,number,integer}]:"};
        r0[150] = new Object[]{"Certificate.fingerprint.SHA1.", "Certificate fingerprint (SHA1): "};
        r0[151] = new Object[]{"Keystore.type.", "Keystore type: "};
        r0[152] = new Object[]{"Keystore.provider.", "Keystore provider: "};
        r0[153] = new Object[]{"Your.keystore.contains.keyStore.size.entry", "Your keystore contains {0,number,integer} entry"};
        r0[154] = new Object[]{"Your.keystore.contains.keyStore.size.entries", "Your keystore contains {0,number,integer} entries"};
        r0[155] = new Object[]{"Failed.to.parse.input", "Failed to parse input"};
        r0[156] = new Object[]{"Empty.input", "Empty input"};
        r0[157] = new Object[]{"Not.X.509.certificate", "Not X.509 certificate"};
        r0[158] = new Object[]{"alias.has.no.public.key", "{0} has no public key"};
        r0[159] = new Object[]{"alias.has.no.X.509.certificate", "{0} has no X.509 certificate"};
        r0[160] = new Object[]{"New.certificate.self.signed.", "New certificate (self-signed):"};
        r0[161] = new Object[]{"Reply.has.no.certificates", "Reply has no certificates"};
        r0[162] = new Object[]{"Certificate.not.imported.alias.alias.already.exists", "Certificate not imported, alias <{0}> already exists"};
        r0[163] = new Object[]{"Input.not.an.X.509.certificate", "Input not an X.509 certificate"};
        r0[164] = new Object[]{"Certificate.already.exists.in.keystore.under.alias.trustalias.", "Certificate already exists in keystore under alias <{0}>"};
        r0[165] = new Object[]{"Do.you.still.want.to.add.it.no.", "Do you still want to add it? [no]:  "};
        r0[166] = new Object[]{"Certificate.already.exists.in.system.wide.CA.keystore.under.alias.trustalias.", "Certificate already exists in system-wide CA keystore under alias <{0}>"};
        r0[167] = new Object[]{"Do.you.still.want.to.add.it.to.your.own.keystore.no.", "Do you still want to add it to your own keystore? [no]:  "};
        r0[168] = new Object[]{"Trust.this.certificate.no.", "Trust this certificate? [no]:  "};
        r0[169] = new Object[]{"YES", "YES"};
        r0[170] = new Object[]{"New.prompt.", "New {0}: "};
        r0[171] = new Object[]{"Passwords.must.differ", "Passwords must differ"};
        r0[172] = new Object[]{"Re.enter.new.prompt.", "Re-enter new {0}: "};
        r0[173] = new Object[]{"Re.enter.new.password.", "Re-enter new password: "};
        r0[174] = new Object[]{"They.don.t.match.Try.again", "They don't match. Try again"};
        r0[175] = new Object[]{"Enter.prompt.alias.name.", "Enter {0} alias name:  "};
        r0[176] = new Object[]{"Enter.new.alias.name.RETURN.to.cancel.import.for.this.entry.", "Enter new alias name\t(RETURN to cancel import for this entry):  "};
        r0[177] = new Object[]{"Enter.alias.name.", "Enter alias name:  "};
        r0[178] = new Object[]{".RETURN.if.same.as.for.otherAlias.", "\t(RETURN if same as for <{0}>)"};
        r0[179] = new Object[]{".PATTERN.printX509Cert", "Owner: {0}\nIssuer: {1}\nSerial number: {2}\nValid from: {3} until: {4}\nCertificate fingerprints:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Signature algorithm name: {8}\n\t Version: {9}"};
        r0[180] = new Object[]{"What.is.your.first.and.last.name.", "What is your first and last name?"};
        r0[181] = new Object[]{"What.is.the.name.of.your.organizational.unit.", "What is the name of your organizational unit?"};
        r0[182] = new Object[]{"What.is.the.name.of.your.organization.", "What is the name of your organization?"};
        r0[183] = new Object[]{"What.is.the.name.of.your.City.or.Locality.", "What is the name of your City or Locality?"};
        r0[184] = new Object[]{"What.is.the.name.of.your.State.or.Province.", "What is the name of your State or Province?"};
        r0[185] = new Object[]{"What.is.the.two.letter.country.code.for.this.unit.", "What is the two-letter country code for this unit?"};
        r0[186] = new Object[]{"Is.name.correct.", "Is {0} correct?"};
        r0[187] = new Object[]{"no", "no"};
        r0[188] = new Object[]{"yes", "yes"};
        r0[189] = new Object[]{"y", "y"};
        r0[190] = new Object[]{".defaultValue.", "  [{0}]:  "};
        r0[191] = new Object[]{"Alias.alias.has.no.key", "Alias <{0}> has no key"};
        r0[192] = new Object[]{"Alias.alias.references.an.entry.type.that.is.not.a.private.key.entry.The.keyclone.command.only.supports.cloning.of.private.key", "Alias <{0}> references an entry type that is not a private key entry.  The -keyclone command only supports cloning of private key entries"};
        r0[193] = new Object[]{".WARNING.WARNING.WARNING.", "*****************  WARNING WARNING WARNING  *****************"};
        r0[194] = new Object[]{"Signer.d.", "Signer #%d:"};
        r0[195] = new Object[]{"Timestamp.", "Timestamp:"};
        r0[196] = new Object[]{"Signature.", "Signature:"};
        r0[197] = new Object[]{"CRLs.", "CRLs:"};
        r0[198] = new Object[]{"Certificate.owner.", "Certificate owner: "};
        r0[199] = new Object[]{"Not.a.signed.jar.file", "Not a signed jar file"};
        r0[HttpURLConnection.HTTP_OK] = new Object[]{"No.certificate.from.the.SSL.server", "No certificate from the SSL server"};
        r0[HttpURLConnection.HTTP_CREATED] = new Object[]{".The.integrity.of.the.information.stored.in.your.keystore.", "* The integrity of the information stored in your keystore  *\n* has NOT been verified!  In order to verify its integrity, *\n* you must provide your keystore password.                  *"};
        r0[HttpURLConnection.HTTP_ACCEPTED] = new Object[]{".The.integrity.of.the.information.stored.in.the.srckeystore.", "* The integrity of the information stored in the srckeystore*\n* has NOT been verified!  In order to verify its integrity, *\n* you must provide the srckeystore password.                *"};
        r0[HttpURLConnection.HTTP_NOT_AUTHORITATIVE] = new Object[]{"Certificate.reply.does.not.contain.public.key.for.alias.", "Certificate reply does not contain public key for <{0}>"};
        r0[HttpURLConnection.HTTP_NO_CONTENT] = new Object[]{"Incomplete.certificate.chain.in.reply", "Incomplete certificate chain in reply"};
        r0[HttpURLConnection.HTTP_RESET] = new Object[]{"Certificate.chain.in.reply.does.not.verify.", "Certificate chain in reply does not verify: "};
        r0[HttpURLConnection.HTTP_PARTIAL] = new Object[]{"Top.level.certificate.in.reply.", "Top-level certificate in reply:\n"};
        r0[207] = new Object[]{".is.not.trusted.", "... is not trusted. "};
        r0[208] = new Object[]{"Install.reply.anyway.no.", "Install reply anyway? [no]:  "};
        r0[209] = new Object[]{"NO", "NO"};
        r0[210] = new Object[]{"Public.keys.in.reply.and.keystore.don.t.match", "Public keys in reply and keystore don't match"};
        r0[211] = new Object[]{"Certificate.reply.and.certificate.in.keystore.are.identical", "Certificate reply and certificate in keystore are identical"};
        r0[212] = new Object[]{"Failed.to.establish.chain.from.reply", "Failed to establish chain from reply"};
        r0[213] = new Object[]{"n", "n"};
        r0[214] = new Object[]{"Wrong.answer.try.again", "Wrong answer, try again"};
        r0[215] = new Object[]{"Secret.key.not.generated.alias.alias.already.exists", "Secret Key not generated, alias <{0}> already exists"};
        r0[216] = new Object[]{"Please.provide.keysize.for.secret.key.generation", "Please provide -keysize for secret key generation"};
        r0[217] = new Object[]{"Extensions.", "Extensions: "};
        r0[218] = new Object[]{".Empty.value.", "(Empty value)"};
        r0[219] = new Object[]{"Extension.Request.", "Extension Request:"};
        r0[220] = new Object[]{"PKCS.10.Certificate.Request.Version.1.0.Subject.s.Public.Key.s.format.s.key.", "PKCS #10 Certificate Request (Version 1.0)\nSubject: %s\nPublic Key: %s format %s key\n"};
        r0[221] = new Object[]{"Unknown.keyUsage.type.", "Unknown keyUsage type: "};
        r0[222] = new Object[]{"Unknown.extendedkeyUsage.type.", "Unknown extendedkeyUsage type: "};
        r0[223] = new Object[]{"Unknown.AccessDescription.type.", "Unknown AccessDescription type: "};
        r0[224] = new Object[]{"Unrecognized.GeneralName.type.", "Unrecognized GeneralName type: "};
        r0[225] = new Object[]{"This.extension.cannot.be.marked.as.critical.", "This extension cannot be marked as critical. "};
        r0[226] = new Object[]{"Odd.number.of.hex.digits.found.", "Odd number of hex digits found: "};
        r0[227] = new Object[]{"Unknown.extension.type.", "Unknown extension type: "};
        r0[228] = new Object[]{"command.{0}.is.ambiguous.", "command {0} is ambiguous:"};
        r0[229] = new Object[]{"Warning.A.public.key.for.alias.signers.i.does.not.exist.Make.sure.a.KeyStore.is.properly.configured.", "Warning: A public key for alias {0} does not exist.  Make sure a KeyStore is properly configured."};
        r0[230] = new Object[]{"Warning.Class.not.found.class", "Warning: Class not found: {0}"};
        r0[231] = new Object[]{"Warning.Invalid.argument.s.for.constructor.arg", "Warning: Invalid argument(s) for constructor: {0}"};
        r0[232] = new Object[]{"Illegal.Principal.Type.type", "Illegal Principal Type: {0}"};
        r0[233] = new Object[]{"Illegal.option.option", "Illegal option: {0}"};
        r0[234] = new Object[]{"Usage.policytool.options.", "Usage: policytool [options]"};
        r0[235] = new Object[]{".file.file.policy.file.location", "  [-file <file>]    policy file location"};
        r0[236] = new Object[]{"New", "New"};
        r0[237] = new Object[]{"Open", "Open"};
        r0[238] = new Object[]{"Save", "Save"};
        r0[239] = new Object[]{"Save.As", "Save As"};
        r0[240] = new Object[]{"View.Warning.Log", "View Warning Log"};
        r0[241] = new Object[]{"Exit", "Exit"};
        r0[242] = new Object[]{"Add.Policy.Entry", "Add Policy Entry"};
        r0[243] = new Object[]{"Edit.Policy.Entry", "Edit Policy Entry"};
        r0[244] = new Object[]{"Remove.Policy.Entry", "Remove Policy Entry"};
        r0[245] = new Object[]{"Edit", "Edit"};
        r0[246] = new Object[]{"Retain", "Retain"};
        r0[247] = new Object[]{"Warning.File.name.may.include.escaped.backslash.characters.It.is.not.necessary.to.escape.backslash.characters.the.tool.escapes", "Warning: File name may include escaped backslash characters. It is not necessary to escape backslash characters (the tool escapes characters as necessary when writing the policy contents to the persistent store).\n\nClick on Retain to retain the entered name, or click on Edit to edit the name."};
        r0[248] = new Object[]{"Add.Public.Key.Alias", "Add Public Key Alias"};
        r0[249] = new Object[]{"Remove.Public.Key.Alias", "Remove Public Key Alias"};
        r0[250] = new Object[]{"File", "File"};
        r0[251] = new Object[]{"KeyStore", "KeyStore"};
        r0[252] = new Object[]{"Policy.File.", "Policy File:"};
        r0[253] = new Object[]{"Could.not.open.policy.file.policyFile.e.toString.", "Could not open policy file: {0}: {1}"};
        r0[254] = new Object[]{"Policy.Tool", "Policy Tool"};
        r0[255] = new Object[]{"Errors.have.occurred.while.opening.the.policy.configuration.View.the.Warning.Log.for.more.information.", "Errors have occurred while opening the policy configuration.  View the Warning Log for more information."};
        r0[256] = new Object[]{"Error", "Error"};
        r0[257] = new Object[]{"OK", "OK"};
        r0[258] = new Object[]{"Status", "Status"};
        r0[259] = new Object[]{"Warning", "Warning"};
        r0[260] = new Object[]{"Permission.", "Permission:                                                       "};
        r0[261] = new Object[]{"Principal.Type.", "Principal Type:"};
        r0[262] = new Object[]{"Principal.Name.", "Principal Name:"};
        r0[263] = new Object[]{"Target.Name.", "Target Name:                                                    "};
        r0[264] = new Object[]{"Actions.", "Actions:                                                             "};
        r0[265] = new Object[]{"OK.to.overwrite.existing.file.filename.", "OK to overwrite existing file {0}?"};
        r0[266] = new Object[]{"Cancel", "Cancel"};
        r0[267] = new Object[]{"CodeBase.", "CodeBase:"};
        r0[268] = new Object[]{"SignedBy.", "SignedBy:"};
        r0[269] = new Object[]{"Add.Principal", "Add Principal"};
        r0[270] = new Object[]{"Edit.Principal", "Edit Principal"};
        r0[271] = new Object[]{"Remove.Principal", "Remove Principal"};
        r0[272] = new Object[]{"Principals.", "Principals:"};
        r0[273] = new Object[]{".Add.Permission", "  Add Permission"};
        r0[274] = new Object[]{".Edit.Permission", "  Edit Permission"};
        r0[275] = new Object[]{"Remove.Permission", "Remove Permission"};
        r0[276] = new Object[]{"Done", "Done"};
        r0[277] = new Object[]{"KeyStore.URL.", "KeyStore URL:"};
        r0[278] = new Object[]{"KeyStore.Type.", "KeyStore Type:"};
        r0[279] = new Object[]{"KeyStore.Provider.", "KeyStore Provider:"};
        r0[280] = new Object[]{"KeyStore.Password.URL.", "KeyStore Password URL:"};
        r0[281] = new Object[]{"Principals", "Principals"};
        r0[282] = new Object[]{".Edit.Principal.", "  Edit Principal:"};
        r0[283] = new Object[]{".Add.New.Principal.", "  Add New Principal:"};
        r0[284] = new Object[]{"Permissions", "Permissions"};
        r0[285] = new Object[]{".Edit.Permission.", "  Edit Permission:"};
        r0[286] = new Object[]{".Add.New.Permission.", "  Add New Permission:"};
        r0[287] = new Object[]{"Signed.By.", "Signed By:"};
        r0[288] = new Object[]{"Cannot.Specify.Principal.with.a.Wildcard.Class.without.a.Wildcard.Name", "Cannot Specify Principal with a Wildcard Class without a Wildcard Name"};
        r0[289] = new Object[]{"Cannot.Specify.Principal.without.a.Name", "Cannot Specify Principal without a Name"};
        r0[290] = new Object[]{"Permission.and.Target.Name.must.have.a.value", "Permission and Target Name must have a value"};
        r0[291] = new Object[]{"Remove.this.Policy.Entry.", "Remove this Policy Entry?"};
        r0[292] = new Object[]{"Overwrite.File", "Overwrite File"};
        r0[293] = new Object[]{"Policy.successfully.written.to.filename", "Policy successfully written to {0}"};
        r0[294] = new Object[]{"null.filename", "null filename"};
        r0[295] = new Object[]{"Save.changes.", "Save changes?"};
        r0[296] = new Object[]{"Yes", "Yes"};
        r0[297] = new Object[]{"No", "No"};
        r0[298] = new Object[]{"Policy.Entry", "Policy Entry"};
        r0[299] = new Object[]{"Save.Changes", "Save Changes"};
        r0[300] = new Object[]{"No.Policy.Entry.selected", "No Policy Entry selected"};
        r0[HttpURLConnection.HTTP_MOVED_PERM] = new Object[]{"Unable.to.open.KeyStore.ex.toString.", "Unable to open KeyStore: {0}"};
        r0[HttpURLConnection.HTTP_MOVED_TEMP] = new Object[]{"No.principal.selected", "No principal selected"};
        r0[HttpURLConnection.HTTP_SEE_OTHER] = new Object[]{"No.permission.selected", "No permission selected"};
        r0[HttpURLConnection.HTTP_NOT_MODIFIED] = new Object[]{"name", "name"};
        r0[HttpURLConnection.HTTP_USE_PROXY] = new Object[]{"configuration.type", "configuration type"};
        r0[306] = new Object[]{"environment.variable.name", "environment variable name"};
        r0[307] = new Object[]{"library.name", "library name"};
        r0[308] = new Object[]{"package.name", "package name"};
        r0[309] = new Object[]{"policy.type", "policy type"};
        r0[310] = new Object[]{"property.name", "property name"};
        r0[311] = new Object[]{"Principal.List", "Principal List"};
        r0[312] = new Object[]{"Permission.List", "Permission List"};
        r0[313] = new Object[]{"Code.Base", "Code Base"};
        r0[314] = new Object[]{"KeyStore.U.R.L.", "KeyStore U R L:"};
        r0[315] = new Object[]{"KeyStore.Password.U.R.L.", "KeyStore Password U R L:"};
        r0[316] = new Object[]{"invalid.null.input.s.", "invalid null input(s)"};
        r0[317] = new Object[]{"actions.can.only.be.read.", "actions can only be 'read'"};
        r0[318] = new Object[]{"permission.name.name.syntax.invalid.", "permission name [{0}] syntax invalid: "};
        r0[319] = new Object[]{"Credential.Class.not.followed.by.a.Principal.Class.and.Name", "Credential Class not followed by a Principal Class and Name"};
        r0[320] = new Object[]{"Principal.Class.not.followed.by.a.Principal.Name", "Principal Class not followed by a Principal Name"};
        r0[321] = new Object[]{"Principal.Name.must.be.surrounded.by.quotes", "Principal Name must be surrounded by quotes"};
        r0[322] = new Object[]{"Principal.Name.missing.end.quote", "Principal Name missing end quote"};
        r0[323] = new Object[]{"PrivateCredentialPermission.Principal.Class.can.not.be.a.wildcard.value.if.Principal.Name.is.not.a.wildcard.value", "PrivateCredentialPermission Principal Class can not be a wildcard (*) value if Principal Name is not a wildcard (*) value"};
        r0[324] = new Object[]{"CredOwner.Principal.Class.class.Principal.Name.name", "CredOwner:\n\tPrincipal Class = {0}\n\tPrincipal Name = {1}"};
        r0[325] = new Object[]{"provided.null.name", "provided null name"};
        r0[326] = new Object[]{"provided.null.keyword.map", "provided null keyword map"};
        r0[327] = new Object[]{"provided.null.OID.map", "provided null OID map"};
        r0[328] = new Object[]{"invalid.null.AccessControlContext.provided", "invalid null AccessControlContext provided"};
        r0[329] = new Object[]{"invalid.null.action.provided", "invalid null action provided"};
        r0[330] = new Object[]{"invalid.null.Class.provided", "invalid null Class provided"};
        r0[331] = new Object[]{"Subject.", "Subject:\n"};
        r0[332] = new Object[]{".Principal.", "\tPrincipal: "};
        r0[333] = new Object[]{".Public.Credential.", "\tPublic Credential: "};
        r0[334] = new Object[]{".Private.Credentials.inaccessible.", "\tPrivate Credentials inaccessible\n"};
        r0[335] = new Object[]{".Private.Credential.", "\tPrivate Credential: "};
        r0[336] = new Object[]{".Private.Credential.inaccessible.", "\tPrivate Credential inaccessible\n"};
        r0[337] = new Object[]{"Subject.is.read.only", "Subject is read-only"};
        r0[338] = new Object[]{"attempting.to.add.an.object.which.is.not.an.instance.of.java.security.Principal.to.a.Subject.s.Principal.Set", "attempting to add an object which is not an instance of java.security.Principal to a Subject's Principal Set"};
        r0[339] = new Object[]{"attempting.to.add.an.object.which.is.not.an.instance.of.class", "attempting to add an object which is not an instance of {0}"};
        r0[340] = new Object[]{"LoginModuleControlFlag.", "LoginModuleControlFlag: "};
        r0[341] = new Object[]{"Invalid.null.input.name", "Invalid null input: name"};
        r0[342] = new Object[]{"No.LoginModules.configured.for.name", "No LoginModules configured for {0}"};
        r0[343] = new Object[]{"invalid.null.Subject.provided", "invalid null Subject provided"};
        r0[344] = new Object[]{"invalid.null.CallbackHandler.provided", "invalid null CallbackHandler provided"};
        r0[345] = new Object[]{"null.subject.logout.called.before.login", "null subject - logout called before login"};
        r0[346] = new Object[]{"unable.to.instantiate.LoginModule.module.because.it.does.not.provide.a.no.argument.constructor", "unable to instantiate LoginModule, {0}, because it does not provide a no-argument constructor"};
        r0[347] = new Object[]{"unable.to.instantiate.LoginModule", "unable to instantiate LoginModule"};
        r0[348] = new Object[]{"unable.to.instantiate.LoginModule.", "unable to instantiate LoginModule: "};
        r0[349] = new Object[]{"unable.to.find.LoginModule.class.", "unable to find LoginModule class: "};
        r0[350] = new Object[]{"unable.to.access.LoginModule.", "unable to access LoginModule: "};
        r0[351] = new Object[]{"Login.Failure.all.modules.ignored", "Login Failure: all modules ignored"};
        r0[352] = new Object[]{"java.security.policy.error.parsing.policy.message", "java.security.policy: error parsing {0}:\n\t{1}"};
        r0[353] = new Object[]{"java.security.policy.error.adding.Permission.perm.message", "java.security.policy: error adding Permission, {0}:\n\t{1}"};
        r0[354] = new Object[]{"java.security.policy.error.adding.Entry.message", "java.security.policy: error adding Entry:\n\t{0}"};
        r0[355] = new Object[]{"alias.name.not.provided.pe.name.", "alias name not provided ({0})"};
        r0[356] = new Object[]{"unable.to.perform.substitution.on.alias.suffix", "unable to perform substitution on alias, {0}"};
        r0[357] = new Object[]{"substitution.value.prefix.unsupported", "substitution value, {0}, unsupported"};
        r0[358] = new Object[]{"LPARAM", "("};
        r0[359] = new Object[]{"RPARAM", ")"};
        r0[360] = new Object[]{"type.can.t.be.null", "type can't be null"};
        r0[361] = new Object[]{"keystorePasswordURL.can.not.be.specified.without.also.specifying.keystore", "keystorePasswordURL can not be specified without also specifying keystore"};
        r0[362] = new Object[]{"expected.keystore.type", "expected keystore type"};
        r0[363] = new Object[]{"expected.keystore.provider", "expected keystore provider"};
        r0[364] = new Object[]{"multiple.Codebase.expressions", "multiple Codebase expressions"};
        r0[365] = new Object[]{"multiple.SignedBy.expressions", "multiple SignedBy expressions"};
        r0[366] = new Object[]{"SignedBy.has.empty.alias", "SignedBy has empty alias"};
        r0[367] = new Object[]{"can.not.specify.Principal.with.a.wildcard.class.without.a.wildcard.name", "can not specify Principal with a wildcard class without a wildcard name"};
        r0[368] = new Object[]{"expected.codeBase.or.SignedBy.or.Principal", "expected codeBase or SignedBy or Principal"};
        r0[369] = new Object[]{"expected.permission.entry", "expected permission entry"};
        r0[370] = new Object[]{"number.", "number "};
        r0[371] = new Object[]{"expected.expect.read.end.of.file.", "expected [{0}], read [end of file]"};
        r0[372] = new Object[]{"expected.read.end.of.file.", "expected [;], read [end of file]"};
        r0[373] = new Object[]{"line.number.msg", "line {0}: {1}"};
        r0[374] = new Object[]{"line.number.expected.expect.found.actual.", "line {0}: expected [{1}], found [{2}]"};
        r0[375] = new Object[]{"null.principalClass.or.principalName", "null principalClass or principalName"};
        r0[376] = new Object[]{"PKCS11.Token.providerName.Password.", "PKCS11 Token [{0}] Password: "};
        r0[377] = new Object[]{"unable.to.instantiate.Subject.based.policy", "unable to instantiate Subject-based policy"};
        contents = r0;
    }

    public Object[][] getContents() {
        return contents;
    }
}
