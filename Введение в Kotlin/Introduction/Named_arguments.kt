fun joinOptions(options: Collection<String>):String {
        val res = options.joinToString(
            separator = ", ",
            prefix = "[",
            postfix = "]",
        )
        return res
}