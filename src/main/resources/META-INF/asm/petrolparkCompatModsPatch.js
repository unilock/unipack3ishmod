var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');
var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');

function initializeCoreMod() {
    return {
        'updateItemUseStartTreshold': {
            'target': {
                'type': 'METHOD',
                'class': 'com.petrolpark.compat.CompatMods',
                'methodName': '<init>',
                'methodDesc': '(Ljava/lang/String;I)V'
            },
            'transformer': function (node) {
                var insn = ASMAPI.findFirstMethodCall(node, ASMAPI.MethodType.STATIC, "com/petrolpark/util/Lang", "asId", "(Ljava/lang/String;)Ljava/lang/String;");
                if (insn != null) {
                    var insnList = ASMAPI.listOf(
                        new FieldInsnNode(Opcodes.GETSTATIC, "java/util/Locale", "ROOT", "Ljava/util/Locale;"),
                        new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String", "toLowerCase", "(Ljava/util/Locale;)Ljava/lang/String;")
                    );
                    ASMAPI.insertInsnList(node, insn, insnList, ASMAPI.InsertMode.REMOVE_ORIGINAL);
                    ASMAPI.log('DEBUG', 'Replaced Lang#asId with String#toLowerCase in CompatMods#<init>');
                }
                return node;
            }
        }
    }
}